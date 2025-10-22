package com.fiscos.user.adapter.secondary.config;

import com.fiscos.user.domain.repository.UserRepository;
import com.fiscos.user.adapter.secondary.database.repository.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository userRepository(UserRepositoryAdapter jpaUserRepository) {
        return jpaUserRepository;
    }

    // outros repositórios podem ser configurados aqui
}
