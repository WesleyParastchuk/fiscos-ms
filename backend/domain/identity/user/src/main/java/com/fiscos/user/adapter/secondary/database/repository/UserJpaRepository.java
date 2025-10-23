package com.fiscos.user.adapter.secondary.database.repository;

import com.fiscos.user.adapter.secondary.database.model.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    Optional<UserJpaEntity> findByAuthEmailValue(String email);
}
