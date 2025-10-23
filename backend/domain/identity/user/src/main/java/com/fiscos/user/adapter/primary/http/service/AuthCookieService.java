package com.fiscos.user.adapter.primary.http.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class AuthCookieService {

    private final String cookieName;
    private final boolean cookieSecure;
    private final boolean cookieHttpOnly;
    private final String cookiePath;
    private final long tokenExpirationInSeconds;

    public AuthCookieService(
            @Value("${fiscos.security.cookie.name}") String cookieName,
            @Value("${fiscos.security.cookie.secure}") boolean cookieSecure,
            @Value("${fiscos.security.cookie.http-only}") boolean cookieHttpOnly,
            @Value("${fiscos.security.cookie.path}") String cookiePath,
            @Value("${fiscos.security.jwt.expiration-ms}") long tokenExpirationInMs) {
        this.cookieName = cookieName;
        this.cookieSecure = cookieSecure;
        this.cookieHttpOnly = cookieHttpOnly;
        this.cookiePath = cookiePath;
        this.tokenExpirationInSeconds = tokenExpirationInMs / 1000;
    }

    public ResponseCookie createAuthCookie(String token) {
        return ResponseCookie.from(cookieName, token)
                .httpOnly(cookieHttpOnly)
                .secure(cookieSecure)
                .path(cookiePath)
                .maxAge(Duration.ofSeconds(tokenExpirationInSeconds))
                .build();
    }

    public ResponseCookie createLogoutCookie() {
        return ResponseCookie.from(cookieName, "")
                .httpOnly(cookieHttpOnly)
                .secure(cookieSecure)
                .path(cookiePath)
                .maxAge(0)
                .build();
    }
}