package com.fiscos.user.adapter.secondary.database.mapper;

import com.fiscos.user.domain.valueobject.Email;
import com.fiscos.user.adapter.secondary.database.model.EmailJpaEntity;

public class EmailMapper {
    public static EmailJpaEntity toEntity(Email email) {
        return new EmailJpaEntity(
            email.getValue()
        );
    }

    public static Email toDomain(EmailJpaEntity emailJpaEntity) {
        return new Email(
            emailJpaEntity.getValue()
        );
    }
}
