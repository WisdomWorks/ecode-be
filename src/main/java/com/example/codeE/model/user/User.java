package com.example.codeE.model.user;

import com.example.codeE.mapper.user.UserFromExcel;
import com.example.codeE.request.user.CreateUserRequest;
import com.example.codeE.security.BCryptPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @Pattern(regexp = "^(teacher|student|admin)$", message = "Role should be teacher, student, or admin")
    private String role;

    @Column(name = "created_date", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.updatedDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }

    public User(@NonNull String userId, @NonNull String name, @NonNull String email, @NonNull String username, @NonNull String role, @NonNull LocalDateTime createdDate, @NonNull LocalDateTime updatedDate) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.role = role;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public User (UserFromExcel excelUser){
        this.userId = excelUser.getUserId();
        this.name = excelUser.getName();
        this.email = excelUser.getEmail();
        this.username = excelUser.getUsername();
        this.password = BCryptPassword.generateRandomPassword();
        this.role = excelUser.getRole();
    }
    public User (CreateUserRequest createUser, String userId){
        this.userId = userId;
        this.username = createUser.getUsername();
        this.name = createUser.getName();
        this.email = createUser.getEmail();
        this.password = BCryptPassword.generateRandomPassword();
        this.role = createUser.getRole();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}