package com.fiscos.user.adapter.secondary.config;

import com.fiscos.user.adapter.secondary.service.JwtTokenAdapter;
import com.fiscos.user.domain.service.PasswordPolicyService;
import com.fiscos.user.domain.service.PasswordService;
import com.fiscos.user.adapter.secondary.service.BcryptAdapter;
import com.fiscos.user.domain.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class AdapterConfig {

    @Bean
    public PasswordPolicyService passwordPolicyService() {
        return new PasswordPolicyService();
    }

    @Bean
    public PasswordService passwordHasher() {
        return new BcryptAdapter();
    }

    @Bean
    public TokenService tokenService(JwtProperties jwtProperties) {
        return new JwtTokenAdapter(
            jwtProperties.secret(),
            jwtProperties.expirationMs()
        );
    }
}

