package com.fiscos.user.adapter.primary.http.security;

import com.fiscos.user.domain.exception.InvalidTokenException;
import com.fiscos.user.domain.service.TokenService; // <-- A Porta do seu Domínio
import com.fiscos.user.domain.valueobject.TokenPayload;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final String cookieName;

    public JwtAuthFilter(TokenService tokenService,
                         @Value("${fiscos.security.cookie.name}") String cookieName) {
        this.tokenService = tokenService;
        this.cookieName = cookieName;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String token = extractTokenFromCookie(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            TokenPayload payload = tokenService.validateToken(token);


            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + payload.getRole().toString());
            List<GrantedAuthority> authorities = Collections.singletonList(authority);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    payload,
                    null,
                    authorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (InvalidTokenException e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private String extractTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}