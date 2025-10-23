package com.fiscos.user.adapter.secondary.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fiscos.security.jwt")
public record JwtProperties(
    String secret,
    long expirationMs
) {}