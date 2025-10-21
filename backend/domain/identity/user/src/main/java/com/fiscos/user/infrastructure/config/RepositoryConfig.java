package com.fiscos.user.infrastructure.config;

import com.fiscos.user.domain.repository.UserRepository;
import com.fiscos.user.infrastructure.persistence.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository userRepository(UserRepositoryImpl jpaUserRepository) {
        return jpaUserRepository;
    }

    // outros repositórios podem ser configurados aqui
}
