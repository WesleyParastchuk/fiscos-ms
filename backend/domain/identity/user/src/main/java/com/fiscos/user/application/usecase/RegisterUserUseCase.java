package com.fiscos.user.application.usecase;

import com.fiscos.user.domain.entity.Auth;
import com.fiscos.user.domain.entity.User;
import com.fiscos.user.domain.exception.EmailAlreadyRegisteredException;
import com.fiscos.user.domain.repository.UserRepository;
import com.fiscos.user.domain.service.PasswordPolicyService;
import com.fiscos.user.domain.valueobject.Email;
import com.fiscos.user.infrastructure.adapter.password.PasswordHasher;

public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final PasswordPolicyService passwordPolicyService;

    public RegisterUserUseCase(UserRepository userRepository, PasswordHasher passwordHasher, PasswordPolicyService passwordPolicyService) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.passwordPolicyService = passwordPolicyService;
    }

    public User execute(String emailStr, String plainPassword, String name) {
        passwordPolicyService.assertValid(plainPassword);

        Email email = new Email(emailStr);

        userRepository.findByEmail(email).ifPresent(existing -> {
            throw new EmailAlreadyRegisteredException(emailStr);
        });

        String passwordHash = passwordHasher.hash(plainPassword);
        Auth auth = new Auth(email, passwordHash);

        User user = new User(name, auth);

        userRepository.save(user);

        return user;
    }
}
