package com.fiscos.user.domain.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordPolicyService {

    public static final int MIN_PASSWORD_LENGTH = 8;

    public boolean isValid(String password) {
        return password != null && password.length() >= 8;
    }

    public void assertValid(String password) {
        if (!isValid(password)) {
            throw new IllegalArgumentException("Password does not meet the policy requirements.");
        }
    }
}

