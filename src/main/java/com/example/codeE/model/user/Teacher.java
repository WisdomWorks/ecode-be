package com.example.codeE.model.user;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Teacher extends User {

    public Teacher(@NonNull String userId, @NonNull String name, @NonNull String email,
            @NonNull String password) {
        super(userId, name, email, password, "teacher");
    }
}
