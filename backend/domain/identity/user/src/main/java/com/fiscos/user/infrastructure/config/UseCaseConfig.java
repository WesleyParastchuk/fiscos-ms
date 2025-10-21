package com.fiscos.user.infrastructure.config;

import com.fiscos.user.application.usecase.RegisterUserUseCase;
import com.fiscos.user.domain.repository.UserRepository;
import com.fiscos.user.domain.service.PasswordPolicyService;
import com.fiscos.user.infrastructure.adapter.password.PasswordHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepository userRepository,
                                                   PasswordHasher passwordHasher,
                                                   PasswordPolicyService passwordPolicyService) {
        return new RegisterUserUseCase(userRepository, passwordHasher, passwordPolicyService);
    }
}
