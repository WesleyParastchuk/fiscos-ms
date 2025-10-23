package com.fiscos.user.application.usecase.autenticate_user;

import com.fiscos.user.application.dto.AuthInputDTO;
import com.fiscos.user.application.dto.AuthOutputDTO;
import com.fiscos.user.application.mapper.AuthMapper;
import com.fiscos.user.domain.entity.User;
import com.fiscos.user.domain.repository.UserRepository;
import com.fiscos.user.domain.service.PasswordService;
import com.fiscos.user.domain.service.TokenService;
import com.fiscos.user.domain.valueobject.Email;
import com.fiscos.user.domain.valueobject.Token;
import com.fiscos.user.domain.valueobject.TokenPayload;

public class AutenticateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final TokenService tokenService;

    public AutenticateUserUseCase(UserRepository userRepository, PasswordService passwordService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.tokenService = tokenService;
    }

    public AuthOutputDTO execute(AuthInputDTO input) {
        Email email = new Email(input.getEmail());
        var userOpt = userRepository.findByEmail(email);

        if(userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found with email: " +  input.getEmail());
        }

        User user = userOpt.get();
        if (!passwordService.matches(input.getPassword(), user.getAuth().getPasswordHash())) {
            throw new IllegalArgumentException("Invalid password");
        }

        TokenPayload payload = new TokenPayload(user.getId().toString(), user.getAuth().getRole());
        Token token = tokenService.generateToken(payload);

        return AuthMapper.toOutput(user, token);
    }
}
