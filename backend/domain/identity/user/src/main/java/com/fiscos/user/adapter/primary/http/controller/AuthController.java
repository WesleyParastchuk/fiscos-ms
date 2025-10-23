package com.fiscos.user.adapter.primary.http.controller;

import com.fiscos.user.adapter.primary.http.dto.auth.AuthRequest;
import com.fiscos.user.adapter.primary.http.dto.auth.AuthResponse;
import com.fiscos.user.adapter.primary.http.mapper.AuthMapper;
import com.fiscos.user.adapter.primary.http.security.IsAnonymous;
import com.fiscos.user.adapter.primary.http.security.IsAuthenticated;
import com.fiscos.user.adapter.primary.http.service.AuthCookieService;
import com.fiscos.user.application.dto.AuthInputDTO;
import com.fiscos.user.application.dto.AuthOutputDTO;
import com.fiscos.user.application.usecase.autenticate_user.AutenticateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AutenticateUserUseCase autenticateUserUseCase;
    private final AuthCookieService authCookieService;

    public AuthController(AutenticateUserUseCase autenticateUserUseCase, AuthCookieService authCookieService) {
        this.autenticateUserUseCase = autenticateUserUseCase;
        this.authCookieService = authCookieService;
    }

    @PostMapping("/login")
    @IsAnonymous
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthInputDTO input = AuthMapper.toInput(request);
        AuthOutputDTO output = autenticateUserUseCase.execute(input);
        AuthResponse body = AuthMapper.toResponse(output);
        ResponseCookie cookie = authCookieService.createAuthCookie(output.getToken());
        return ResponseEntity.ok().
                header("Set-Cookie", cookie.toString()).
                body(body);
    }

    @PostMapping("/logout")
    @IsAuthenticated
    public ResponseEntity<Void> logout() {
        ResponseCookie cookie = authCookieService.createLogoutCookie();
        return ResponseEntity.ok().
                header("Set-Cookie", cookie.toString()).
                build();
    }
}
