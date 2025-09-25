package com.zaneta.japantrip.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPatchRequest {

    @Size(max = 50,message = "FirstName must be at most 50 characters")
    private String firstName;

    @Size(max = 50,message = "LastName must be at most 50 characters")
    private String lastName;

    @Size(max = 50,message = "Nickname must be at most 50 characters")
    private String nickname;

    @Email(message = "Email must be valid")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'|,.<>/?]).{8,}$",
            message = "Password must contain at least 1 number, 1 uppercase letter and 1 special character"
    )
    private String password;

}

