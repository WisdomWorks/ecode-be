package com.example.codeE.service.authentication;

import com.example.codeE.model.user.User;
import com.example.codeE.request.user.LoginRequest;
import com.example.codeE.request.user.UserAuthenRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenService {
    UserAuthenRequest signIn(LoginRequest signInRequest, HttpServletResponse response);
    User getUserToken(String token);
}
