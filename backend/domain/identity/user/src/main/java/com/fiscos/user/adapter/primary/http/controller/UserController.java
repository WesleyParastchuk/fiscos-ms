package com.fiscos.user.adapter.primary.http.controller;

import com.fiscos.user.application.usecase.register_user.RegisterUserInputDTO;
import com.fiscos.user.application.dto.AuthOutputDTO;
import com.fiscos.user.application.usecase.register_user.RegisterUserUseCase;
import com.fiscos.user.adapter.primary.http.dto.auth.AuthResponse;
import com.fiscos.user.adapter.primary.http.dto.user.RegisterUserRequest;
import com.fiscos.user.adapter.primary.http.mapper.AuthMapper;
import com.fiscos.user.adapter.primary.http.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
class UserController {

    private final RegisterUserUseCase registerUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        RegisterUserInputDTO input = UserMapper.toInputDTO(request);
        AuthOutputDTO output = registerUserUseCase.execute(input);

        return ResponseEntity.ok(AuthMapper.toResponse(output));
    }
}