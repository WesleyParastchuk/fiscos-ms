package com.fiscos.user.domain.valueobject;

import java.time.Instant;

public class Token {
    private final String value;
    private final Instant expiration;
    private final TokenPayload payload;

    public Token(String value, Instant expiration, TokenPayload payload) {
        this.value = value;
        this.expiration = expiration;
        this.payload = payload;
    }

    public String getValue() {
        return value;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public TokenPayload getPayload() {
        return payload;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiration);
    }
}
