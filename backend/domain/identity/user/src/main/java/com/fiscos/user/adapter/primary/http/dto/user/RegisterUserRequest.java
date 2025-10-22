package com.fiscos.user.adapter.primary.http.dto.user;

import com.fiscos.user.domain.service.PasswordPolicyService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterUserRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = PasswordPolicyService.MIN_PASSWORD_LENGTH, message = "Password must be at least " + PasswordPolicyService.MIN_PASSWORD_LENGTH + " characters long")
    private String password;

}
