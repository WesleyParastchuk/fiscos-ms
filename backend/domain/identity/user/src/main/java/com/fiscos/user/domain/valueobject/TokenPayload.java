package com.fiscos.user.domain.valueobject;

import lombok.Getter;

@Getter
public class TokenPayload {
    private final String userId;
    private final Role role;

    public TokenPayload(String userId, Role role) {
        this.userId = userId;
        this.role = role;
    }

}
