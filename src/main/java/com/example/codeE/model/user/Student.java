package com.example.codeE.model.user;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Student extends User{

    public Student(@NonNull String userId, @NonNull String name, @NonNull String email, @NonNull String userName,
            @NonNull String password, @NonNull String role, @NonNull LocalDateTime created_date, @NonNull LocalDateTime updated_date) {
        super(userId,userName, name, email, password, role, created_date, updated_date);
    }
    
}
