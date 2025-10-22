package com.fiscos.user.adapter.secondary.database.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthJpaEntity {

    @Embedded
    private EmailJpaEntity email;

    private String passwordHash;

}

