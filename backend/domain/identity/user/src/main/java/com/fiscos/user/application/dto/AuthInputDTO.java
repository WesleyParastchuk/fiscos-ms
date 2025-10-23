package com.fiscos.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthInputDTO {
    private final String email;
    private final String password;
}
