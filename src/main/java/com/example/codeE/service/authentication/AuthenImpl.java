package com.example.codeE.service.authentication;

import com.example.codeE.helper.JWTUtils;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.user.LoginRequest;
import com.example.codeE.request.user.UserAuthenRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenImpl implements  AuthenService{
    @Autowired
    private UserRepository userRepository;
    JWTUtils jwtHelper = new JWTUtils();
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserAuthenRequest signIn(LoginRequest signInRequest, HttpServletResponse response) {
        UserAuthenRequest userResponse = new UserAuthenRequest();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUserName(),signInRequest.getPassword()));
            var user = userRepository.findUserByUserName(signInRequest.getUserName());
            var jwt = jwtHelper.generateToken(user);
            var refreshToken = jwtHelper.generateRefreshToken(new HashMap<>(), user);
            userResponse.setStatusCode(200);
            userResponse.setToken(jwt);
            userResponse.setRefreshToken(refreshToken);
            userResponse.setExpirationTime("1 hour");
            userResponse.setMessage("Sign In successful");
            ResponseCookie cookie = ResponseCookie.from("accessToken", jwt)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(3600)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }catch (Exception e){
            userResponse.setStatusCode(500);
            userResponse.setMessage("Access denied");
            userResponse.setError(e.getMessage());
        }
        return userResponse;
    }

    @Override
    public User getUserToken(String token) {
        String username = this.jwtHelper.extractUserName(token);
        return this.userRepository.findUserByUserName(username);
    }
}
