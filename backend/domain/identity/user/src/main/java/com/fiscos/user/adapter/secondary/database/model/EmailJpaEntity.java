package com.fiscos.user.adapter.secondary.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailJpaEntity {

    @Column(nullable = false, unique = true, updatable = false)
    private String value;

}
