package com.fiscos.user.infrastructure.mapper;

import com.fiscos.user.domain.valueobject.Email;
import com.fiscos.user.infrastructure.persistence.entity.EmailEntity;

public class EmailEntityMapper {
    public static EmailEntity toEntity(Email email) {
        return new EmailEntity(
            email.getValue()
        );
    }

    public static Email toDomain(EmailEntity emailEntity) {
        return new Email(
            emailEntity.getValue()
        );
    }
}
