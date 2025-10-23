package com.fiscos.user.adapter.secondary.database.mapper;

import com.fiscos.user.domain.entity.Auth;
import com.fiscos.user.adapter.secondary.database.model.AuthJpaEntity;

public class AuthMapper {
    public static AuthJpaEntity toEntity(Auth auth) {
        AuthJpaEntity authJpaEntity = new AuthJpaEntity();
        authJpaEntity.setEmail(EmailMapper.toEntity(auth.getEmail()));
        authJpaEntity.setPasswordHash(auth.getPasswordHash());
        authJpaEntity.setRole(auth.getRole());
        return authJpaEntity;
    }

    public static Auth toDomain(AuthJpaEntity authJpaEntity) {
        return new Auth(
                EmailMapper.toDomain(authJpaEntity.getEmail()),
                authJpaEntity.getPasswordHash(),
                authJpaEntity.getRole()
        );
    }
}
