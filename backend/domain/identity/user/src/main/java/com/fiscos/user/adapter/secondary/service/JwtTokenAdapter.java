package com.fiscos.user.adapter.secondary.service;

import com.fiscos.user.domain.service.TokenService;
import com.fiscos.user.domain.valueobject.Token;
import com.fiscos.user.domain.valueobject.TokenPayload;
import com.fiscos.user.domain.exception.InvalidTokenException;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenAdapter implements TokenService {

    private final long jwtExpirationInMs;

    private final SecretKey key;

    public JwtTokenAdapter(String jwtSecret, long jwtExpirationInMs) {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    @Override
    public Token generateToken(TokenPayload payload) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", payload.getUserId());

        String tokenString = Jwts.builder()
                .setClaims(claims)
                .setSubject(payload.getUserId())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new Token(tokenString, expiryDate.toInstant(), payload);
    }

    @Override
    public TokenPayload validateToken(String tokenStr) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(tokenStr)
                    .getBody();

            return new TokenPayload(
                    claims.getSubject()
            );

        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new InvalidTokenException("Token JWT inválido ou malformado.");
        } catch (ExpiredJwtException ex) {
            throw new InvalidTokenException("Token JWT expirado.");
        }
    }
}