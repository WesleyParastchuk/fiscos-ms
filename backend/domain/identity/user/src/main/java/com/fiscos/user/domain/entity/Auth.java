package com.fiscos.user.domain.entity;


import com.fiscos.user.domain.valueobject.Email;
import lombok.Getter;

@Getter
public class Auth {

    private final Email email;
    private String passwordHash;

    public Auth(Email email, String passwordHash) {
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("Invalid password");
        }
        this.email = email;
        this.passwordHash = passwordHash;
    }

}

