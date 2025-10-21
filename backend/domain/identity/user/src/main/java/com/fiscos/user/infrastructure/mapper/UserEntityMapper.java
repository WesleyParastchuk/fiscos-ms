package com.fiscos.user.infrastructure.mapper;

import com.fiscos.user.domain.entity.User;
import com.fiscos.user.infrastructure.persistence.entity.AuthEntity;
import com.fiscos.user.infrastructure.persistence.entity.UserEntity;

import java.util.UUID;

public class UserEntityMapper {
    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId().toString());
        userEntity.setName(user.getName());
        userEntity.setActive(user.isActive());
        userEntity.setCreatedAt(user.getCreatedAt());

        AuthEntity authEntity = AuthEntityMapper.toEntity(user.getAuth());
        userEntity.setAuth(authEntity);

        return userEntity;
    }

    public static User toDomain(UserEntity userEntity) {
        User user = new User(
                UUID.fromString(userEntity.getId()),
                userEntity.getName(),
                AuthEntityMapper.toDomain(userEntity.getAuth()),
                userEntity.isActive(),
                userEntity.getCreatedAt()
        );

        return user;
    }
}
