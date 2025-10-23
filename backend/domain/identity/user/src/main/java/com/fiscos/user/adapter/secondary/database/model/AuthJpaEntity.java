package com.fiscos.user.adapter.secondary.database.model;

import com.fiscos.user.domain.valueobject.Role;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

}

