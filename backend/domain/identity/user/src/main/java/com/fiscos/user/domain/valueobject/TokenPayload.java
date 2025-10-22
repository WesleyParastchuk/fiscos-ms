package com.fiscos.user.domain.valueobject;

import java.util.UUID;

public class TokenPayload {
    private final String userId;

    public TokenPayload(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

}
