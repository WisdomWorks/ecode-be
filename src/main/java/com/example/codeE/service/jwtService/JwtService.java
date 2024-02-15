package com.example.codeE.service.jwtService;

import java.util.function.Function;

import com.example.codeE.model.user.User;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String extractUserName(String token);
    Claims extractAllClaims(String token);
    Boolean isTokenInvalid(String token, User user);
    <T> T extractClaims(String token, Function<Claims, T> claimsResolver);
}
