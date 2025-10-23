package com.fiscos.user.application.usecase.register_user;

import lombok.Getter;

@Getter
public class RegisterUserInputDTO {
    private final String name;
    private final String email;
    private final String plainPassword;

    public RegisterUserInputDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.plainPassword = password;
    }
}
