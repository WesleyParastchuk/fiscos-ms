package com.fiscos.user.domain.repository;

import com.fiscos.user.domain.entity.User;
import com.fiscos.user.domain.valueobject.Email;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(String id);

    Optional<User> findByEmail(Email email);
}

