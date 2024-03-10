package com.example.codeE.service.authentication;

import com.example.codeE.model.user.User;
import com.example.codeE.request.user.LoginRequest;
import com.example.codeE.request.user.UserAuthenRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenService {
    UserAuthenRequest signInAdmin(LoginRequest signInRequest, HttpServletResponse response);
    UserAuthenRequest signIn(LoginRequest signInRequest, HttpServletResponse response);
    UserAuthenRequest createNewSession(String token, HttpServletResponse response);
    User getUserToken(String token);
}
