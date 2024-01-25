package com.example.codeE.model.user;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Admin extends User{

    public Admin(@NonNull String userId, @NonNull String name, @NonNull String email, @NonNull String userName,
            @NonNull String password, @NonNull String role) {
        super(userId, name, email, userName, password, role);
    }
    
}
