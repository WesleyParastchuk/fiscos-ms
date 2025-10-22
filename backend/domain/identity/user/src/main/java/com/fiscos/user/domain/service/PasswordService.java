package com.fiscos.user.domain.service;

public interface PasswordService {
    String hash(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
