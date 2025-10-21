package com.fiscos.user.infrastructure.mapper;

import com.fiscos.user.domain.entity.Auth;
import com.fiscos.user.infrastructure.persistence.entity.AuthEntity;
import com.fiscos.user.infrastructure.persistence.entity.EmailEntity;

public class AuthEntityMapper {
    public static AuthEntity toEntity(Auth auth) {
        AuthEntity authEntity = new AuthEntity();
        authEntity.setEmail(EmailEntityMapper.toEntity(auth.getEmail()));
        authEntity.setPasswordHash(auth.getPasswordHash());
        return authEntity;
    }

    public static Auth toDomain(AuthEntity authEntity) {
        Auth auth = new Auth(
                EmailEntityMapper.toDomain(authEntity.getEmail()),
                authEntity.getPasswordHash()
        );
        return auth;
    }
}
