package com.fiscos.user.adapter.primary.http.controller;

import com.fiscos.user.adapter.primary.http.dto.auth.AuthRequest;
import com.fiscos.user.adapter.primary.http.dto.auth.AuthResponse;
import com.fiscos.user.adapter.primary.http.mapper.AuthMapper;
import com.fiscos.user.application.dto.AuthInputDTO;
import com.fiscos.user.application.dto.AuthOutputDTO;
import com.fiscos.user.application.usecase.autenticate_user.AutenticateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AutenticateUserUseCase autenticateUserUseCase;

    public AuthController(AutenticateUserUseCase autenticateUserUseCase) {
        this.autenticateUserUseCase = autenticateUserUseCase;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        AuthInputDTO input = AuthMapper.toInput(request);
        AuthOutputDTO output = autenticateUserUseCase.execute(input);
        return AuthMapper.toResponse(output);
    }
}
