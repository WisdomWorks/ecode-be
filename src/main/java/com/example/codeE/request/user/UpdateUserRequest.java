package com.example.codeE.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String updatedName;

    @Email(message = "Invalid email format")
    private String updatedEmail;

    private String updatedUsername;

    @Size(min = 6, message = "Password should have at least 6 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).*$", message = "Password should contain at least one letter and one digit")
    private String updatedPassword;

    @Pattern(regexp = "^(teacher|student|admin)$", message = "Role should be teacher, student, or admin")
    private String updatedRole;
}
