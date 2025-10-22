package com.fiscos.user.adapter.secondary.config;

import com.fiscos.user.application.usecase.autenticate_user.AutenticateUserUseCase;
import com.fiscos.user.application.usecase.register_user.RegisterUserUseCase;
import com.fiscos.user.domain.repository.UserRepository;
import com.fiscos.user.domain.service.PasswordService;
import com.fiscos.user.domain.service.PasswordPolicyService;
import com.fiscos.user.domain.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepository userRepository,
                                                   PasswordService passwordService,
                                                   PasswordPolicyService passwordPolicyService,
                                                   TokenService tokenService) {
        return new RegisterUserUseCase(userRepository, passwordService, passwordPolicyService, tokenService);
    }

    @Bean
    public AutenticateUserUseCase autenticateUserUseCase(UserRepository userRepository,
                                                         PasswordService passwordService,
                                                         TokenService tokenService) {
        return new AutenticateUserUseCase(userRepository, passwordService, tokenService);
    }
}
