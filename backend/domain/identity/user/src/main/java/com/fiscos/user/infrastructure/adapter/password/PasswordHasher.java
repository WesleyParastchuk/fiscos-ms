package com.fiscos.user.infrastructure.adapter.password;

public interface PasswordHasher {
    String hash(String plainPassword);
    boolean verify(String plainPassword, String hash);
}
