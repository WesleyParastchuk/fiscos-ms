package com.fiscos.user.interfaces.dto.auth;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AuthResponse {

    private final UUID userId;
    private final String token;

    public AuthResponse(UUID userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
