package com.zaneta.japantrip.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(@NotBlank(message = "Email is required")
                               @Email(message = "Email must be valid") String email,
                               @NotBlank(message = "Password is required") String password) {
}

