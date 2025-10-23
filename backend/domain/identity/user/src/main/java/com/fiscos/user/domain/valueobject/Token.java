package com.fiscos.user.domain.valueobject;

import lombok.Getter;

import java.time.Instant;

@Getter
public class Token {
    private final String value;
    private final Instant expiration;
    private final TokenPayload payload;

    public Token(String value, Instant expiration, TokenPayload payload) {
        this.value = value;
        this.expiration = expiration;
        this.payload = payload;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiration);
    }
}
