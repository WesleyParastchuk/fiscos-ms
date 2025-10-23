package com.fiscos.user.domain.entity;


import com.fiscos.user.domain.valueobject.Email;
import com.fiscos.user.domain.valueobject.Role;
import lombok.Getter;

@Getter
public class Auth {

    private final Email email;
    private final String passwordHash;
    private final Role role;

    public Auth(Email email, String passwordHash, Role role) {
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("Invalid password");
        }
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

}

