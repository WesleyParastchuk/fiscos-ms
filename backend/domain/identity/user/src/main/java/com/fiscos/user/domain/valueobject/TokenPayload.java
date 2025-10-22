package com.fiscos.user.domain.valueobject;

import lombok.Getter;

@Getter
public class TokenPayload {
    private final String userId;

    public TokenPayload(String userId) {
        this.userId = userId;
    }

}
