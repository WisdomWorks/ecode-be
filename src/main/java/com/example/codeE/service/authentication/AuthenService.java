package com.example.codeE.service.authentication;

import com.example.codeE.model.user.User;
import com.example.codeE.request.user.UserAuthenRequest;

public interface AuthenService {
    UserAuthenRequest signIn(UserAuthenRequest signInRequest);
}
