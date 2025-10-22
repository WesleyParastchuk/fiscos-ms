package com.fiscos.user.adapter.primary.http.mapper;

import com.fiscos.user.application.usecase.register_user.RegisterUserInputDTO;
import com.fiscos.user.adapter.primary.http.dto.user.RegisterUserRequest;

public class UserMapper {
    public static RegisterUserInputDTO toInputDTO(RegisterUserRequest request) {
        return new RegisterUserInputDTO(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
    }
}
