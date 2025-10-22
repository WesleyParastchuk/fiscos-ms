package com.fiscos.user.domain.service;

import com.fiscos.user.domain.valueobject.Token;
import com.fiscos.user.domain.valueobject.TokenPayload;

public interface TokenService {
    Token generateToken(TokenPayload payload);
     TokenPayload validateToken(String tokenStr);
}
