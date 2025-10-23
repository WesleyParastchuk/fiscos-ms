package com.fiscos.user.adapter.secondary.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserJpaEntity {

    @Id
    private String id;

    private String name;

    @Embedded
    private AuthJpaEntity auth;

    private boolean active;

    private LocalDateTime createdAt;

}
