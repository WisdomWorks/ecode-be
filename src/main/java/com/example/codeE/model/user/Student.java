package com.example.codeE.model.user;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Student extends User{

    public Student(@NonNull String userId, @NonNull String name, @NonNull String email, @NonNull String userName,
            @NonNull String password, @NonNull String role) {
        super(userId, name, email, password, role);
    }
    
}
