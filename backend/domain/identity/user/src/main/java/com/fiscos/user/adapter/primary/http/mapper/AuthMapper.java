package com.fiscos.user.adapter.primary.http.mapper;

import com.fiscos.user.adapter.primary.http.dto.auth.AuthRequest;
import com.fiscos.user.application.dto.AuthInputDTO;
import com.fiscos.user.application.dto.AuthOutputDTO;
import com.fiscos.user.adapter.primary.http.dto.auth.AuthResponse;

public class AuthMapper {
    public static AuthResponse toResponse(AuthOutputDTO output) {
        return new AuthResponse(
                output.getUserId(),
                output.getName(),
                output.getEmail(),
                output.getRole().toString(),
                output.getToken()
        );
    }

    public static AuthInputDTO toInput(AuthRequest request) {
        return new AuthInputDTO(
                request.getEmail(),
                request.getPassword()
        );
    }
}
