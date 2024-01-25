package com.example.codeE.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {
    @NonNull
    private String userId;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private String role;
}