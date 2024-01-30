package com.example.codeE.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @NotBlank(message = "User id is required")
    @Column(name = "user_id")
    private String userId;

    @Column(name = "name")
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "email")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "username")
    @NotBlank(message = "Username is required")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should have at least 6 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).*$", message = "Password should contain at least one letter and one digit")
    private String password;

    @Column(name = "role")
    @NotBlank(message = "Role is required")
    @Pattern(regexp = "^(Teacher|Student|Admin)$", message = "Role should be Teacher, Student, or Admin")
    private String role;

    @Column(name = "created_date")
    @NotBlank(message = "Created date is required")
    private String createdDate;

    @Column(name = "updated_date")
    @NotBlank(message = "Updated date is required")
    private String updatedDate;

    public User(@NonNull String userId, @NonNull String name, @NonNull String email, @NonNull String username, @NonNull String role, @NonNull String createdDate, @NonNull String updatedDate) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.role = role;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}