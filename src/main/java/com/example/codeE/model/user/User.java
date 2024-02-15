package com.example.codeE.model.user;

import com.example.codeE.mapper.user.UserFromExcel;
import com.example.codeE.security.BCryptPassword;
import com.example.codeE.util.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User implements UserDetails, CredentialsContainer{
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

    @Column(name = "created_date")
    @NotBlank(message = "Created date is required")
    private String createdDate;

    @Column(name = "updated_date")
    @NotBlank(message = "Updated date is required")
    private String updatedDate;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdDate = DateTimeUtil.format(now);
        updatedDate = DateTimeUtil.format(now);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = DateTimeUtil.format(LocalDateTime.now());
    }

    public User(@NonNull String userId, @NonNull String name, @NonNull String email, @NonNull String username, @NonNull String role, @NonNull String createdDate, @NonNull String updatedDate) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Object orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }

    @Override
    public void eraseCredentials() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eraseCredentials'");
    }

}