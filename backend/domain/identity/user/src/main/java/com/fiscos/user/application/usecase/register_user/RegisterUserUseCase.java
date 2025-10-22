package com.fiscos.user.application.usecase.register_user;

import com.fiscos.user.application.dto.AuthOutputDTO;
import com.fiscos.user.application.mapper.AuthMapper;
import com.fiscos.user.domain.entity.Auth;
import com.fiscos.user.domain.entity.User;
import com.fiscos.user.domain.exception.EmailAlreadyRegisteredException;
import com.fiscos.user.domain.repository.UserRepository;
import com.fiscos.user.domain.service.PasswordService;
import com.fiscos.user.domain.service.PasswordPolicyService;
import com.fiscos.user.domain.service.TokenService;
import com.fiscos.user.domain.valueobject.Email;
import com.fiscos.user.domain.valueobject.Token;
import com.fiscos.user.domain.valueobject.TokenPayload;

public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final PasswordPolicyService passwordPolicyService;
    private final TokenService tokenService;

    public RegisterUserUseCase(UserRepository userRepository, PasswordService passwordService, PasswordPolicyService passwordPolicyService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.passwordPolicyService = passwordPolicyService;
        this.tokenService = tokenService;
    }

    public AuthOutputDTO execute(RegisterUserInputDTO input) {
        passwordPolicyService.assertValid(input.getPlainPassword());

        Email email = new Email(input.getEmail());
        userRepository.findByEmail(email).ifPresent(existing -> {
            throw new EmailAlreadyRegisteredException(input.getEmail());
        });

        String passwordHash = passwordService.hash(input.getPlainPassword());

        Auth auth = new Auth(email, passwordHash);
        User user = new User(input.getName(), auth);
        userRepository.save(user);

        TokenPayload payload = new TokenPayload(user.getId().toString());
        Token token = tokenService.generateToken(payload);

        return AuthMapper.toOutput(user, token);
    }
}
