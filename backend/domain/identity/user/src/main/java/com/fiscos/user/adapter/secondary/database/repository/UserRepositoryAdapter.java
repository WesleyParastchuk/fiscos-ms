package com.fiscos.user.adapter.secondary.database.repository;

import com.fiscos.user.domain.entity.User;
import com.fiscos.user.domain.repository.UserRepository;
import com.fiscos.user.domain.valueobject.Email;
import com.fiscos.user.adapter.secondary.database.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(User user) {
        var entity = UserMapper.toEntity(user);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<User> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id))
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return jpaRepository.findByAuthEmailValue(email.getValue())
                .map(UserMapper::toDomain);
    }
}
