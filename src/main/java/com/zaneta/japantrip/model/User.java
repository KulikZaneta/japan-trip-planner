package com.zaneta.japantrip.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    /* H2 test
    @GeneratedValue(generator = "UUID")
    @GenericGenerator.*/
    private UUID userId;

    @NotBlank(message = "FirstName is required")
    @Size(max = 50,message = "FirstName must be at most 50 characters")
    private String firstName;

    @NotBlank(message = "LastName is required")
    @Size(max = 50,message = "LastName must be at most 50 characters")
    private String lastName;

    @NotBlank(message = "Nickname is required")
    @Size(max = 50,message = "Nickname must be at most 50 characters")
    @Column(unique = true)
    private String nickName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
}

