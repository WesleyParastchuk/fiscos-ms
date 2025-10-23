package com.fiscos.user.application.mapper;

import com.fiscos.user.application.dto.AuthOutputDTO;
import com.fiscos.user.domain.entity.User;
import com.fiscos.user.domain.valueobject.Token;

public class AuthMapper {
    public static AuthOutputDTO toOutput(User user, Token token) {
        return new AuthOutputDTO(
                user.getId().toString(),
                user.getName(),
                user.getAuth().getEmail().getValue(),
                user.getAuth().getRole(),
                token.getValue()
        );
    }
}
