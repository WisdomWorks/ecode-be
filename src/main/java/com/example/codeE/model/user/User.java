package com.example.codeE.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @NonNull
    @Column(name = "user_id")
    private String userId;
    @Column(name = "name")
    @NonNull
    private String name;
    @Column(name = "email")
    @NonNull
    private String email;
    @Column(name = "username")
    @NonNull
    private String username;
    @JsonIgnore
    @Column(name = "password")
    @NonNull
    private String password;
    @Column(name = "role")
    @NonNull
    private String role;
    @Column(name = "created_date")
    @NonNull
    private String createdDate;
    @Column(name = "updated_date")
    @NonNull
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