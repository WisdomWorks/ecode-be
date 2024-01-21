package com.example.codeE.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class User {
    //email, userName, Password, role
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