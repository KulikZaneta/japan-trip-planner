package com.zaneta.japantrip.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequest(@NotBlank(message = "First name is required")
                                      @Size(max = 50, message = "First name must be at most 50 characters")
                                      String firstName,

                                      @NotBlank(message = "Last name is required")
                                      @Size(max = 50, message = "Last name must be at most 50 characters")
                                      String lastName,

                                      @NotBlank(message = "Nickname is required")
                                      @Size(max = 50, message = "Nickname must be at most 50 characters")
                                      String nickname,

                                      @NotBlank(message = "Email is required")
                                      @Email(message = "Email must be valid")
                                      String email,

                                      @NotBlank(message = "Password is required")
                                      @Size(min = 8, message = "Password must be at least 8 characters")
                                      @Pattern(
                                              regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'|,.<>/?]).{8,}$",
                                              message = "Password must contain at least 1 number, 1 uppercase letter and 1 special character"
                                      )
                                      String password) {
}
