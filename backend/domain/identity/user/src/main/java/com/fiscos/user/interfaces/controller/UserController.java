package com.fiscos.user.interfaces.controller;

import com.fiscos.user.application.usecase.RegisterUserUseCase;
import com.fiscos.user.domain.entity.User;
import com.fiscos.user.interfaces.dto.auth.AuthResponse;
import com.fiscos.user.interfaces.dto.user.RegisterUserRequest;
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
        User user = registerUserUseCase.execute(request.getEmail(), request.getPassword(), request.getName());

        return ResponseEntity.ok(new AuthResponse(user.getId() , user.getId().toString()));
    }
}