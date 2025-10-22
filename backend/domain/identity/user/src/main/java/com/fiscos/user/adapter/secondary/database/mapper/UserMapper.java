package com.fiscos.user.adapter.secondary.database.mapper;

import com.fiscos.user.domain.entity.User;
import com.fiscos.user.adapter.secondary.database.model.AuthJpaEntity;
import com.fiscos.user.adapter.secondary.database.model.UserJpaEntity;

import java.util.UUID;

public class UserMapper {
    public static UserJpaEntity toEntity(User user) {
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.setId(user.getId().toString());
        userJpaEntity.setName(user.getName());
        userJpaEntity.setActive(user.isActive());
        userJpaEntity.setCreatedAt(user.getCreatedAt());

        AuthJpaEntity authJpaEntity = AuthMapper.toEntity(user.getAuth());
        userJpaEntity.setAuth(authJpaEntity);

        return userJpaEntity;
    }

    public static User toDomain(UserJpaEntity userJpaEntity) {
        return new User(
                UUID.fromString(userJpaEntity.getId()),
                userJpaEntity.getName(),
                AuthMapper.toDomain(userJpaEntity.getAuth()),
                userJpaEntity.isActive(),
                userJpaEntity.getCreatedAt()
        );
    }
}
