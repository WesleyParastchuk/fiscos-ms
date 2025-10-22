package com.fiscos.user.adapter.primary.http.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthResponse {
    private final String userId;
    private final String name;
    private final String email;
    private final String token;
}
