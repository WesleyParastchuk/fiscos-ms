package com.fiscos.user.infrastructure.config;

import com.fiscos.user.infrastructure.adapter.password.BcryptAdapter;
import com.fiscos.user.infrastructure.adapter.password.PasswordHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {

    @Bean
    public PasswordHasher passwordHasher() {
        return new BcryptAdapter();
    }

}

