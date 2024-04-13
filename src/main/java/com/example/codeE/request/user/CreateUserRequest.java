package com.example.codeE.request.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @Column(name = "name")
    @NotBlank(message = "Name is required")
    private String name;
    @Column(name = "email")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @Column(name = "username")
    @NotBlank(message = "Username is required")
    @Size(min = 8, max = 8, message = "Username should be 8 characters")
    private String username;
    @Column(name = "role")
    @NotBlank(message = "Role is required")
    @Pattern(regexp = "^(teacher|student|admin)$", message = "Role should be teacher, student, or admin")
    private String role;
}
