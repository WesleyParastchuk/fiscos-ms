package com.fiscos.user.application.dto;

import com.fiscos.user.domain.valueobject.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthOutputDTO {
    private final String userId;
    private final String name;
    private final String email;
    private final Role role;
    private final String token;
}
