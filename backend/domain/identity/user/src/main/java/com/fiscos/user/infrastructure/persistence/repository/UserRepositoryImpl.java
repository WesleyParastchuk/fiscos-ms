package com.fiscos.user.infrastructure.persistence.repository;

import com.fiscos.user.domain.entity.User;
import com.fiscos.user.domain.repository.UserRepository;
import com.fiscos.user.domain.valueobject.Email;
import com.fiscos.user.infrastructure.mapper.UserEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryImpl(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(User user) {
        var entity = UserEntityMapper.toEntity(user);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<User> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id))
                .map(UserEntityMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return jpaRepository.findByAuthEmailValue(email.getValue())
                .map(UserEntityMapper::toDomain);
    }
}
