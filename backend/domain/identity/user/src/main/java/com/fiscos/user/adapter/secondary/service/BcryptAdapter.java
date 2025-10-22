package com.fiscos.user.adapter.secondary.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fiscos.user.domain.service.PasswordService;

public class BcryptAdapter implements PasswordService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String hash(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    @Override
    public boolean matches(String plainPassword, String hash) {
        return encoder.matches(plainPassword, hash);
    }

}
