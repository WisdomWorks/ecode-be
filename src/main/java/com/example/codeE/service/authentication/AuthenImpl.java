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
            if(user.getRole().equals("admin")){
                userResponse.setStatusCode(403);
                userResponse.setMessage("Something wrong when login");
                userResponse.setError("User is admin");
                return userResponse;
            }
            userResponse = this.setResponseResult(user);
            ResponseCookie cookie = ResponseCookie.from("accessToken", userResponse.getToken())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(3600)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }catch (Exception e){
            userResponse.setStatusCode(500);
            userResponse.setMessage("Something wrong when login");
            userResponse.setError(e.getMessage());
        }
        return userResponse;
    }

    @Override
    public UserAuthenRequest createNewSession(String token, HttpServletResponse response) {
        UserAuthenRequest userResponse = new UserAuthenRequest();
        try{
            var userName = this.jwtHelper.extractUserName(token);
            if(userName.isBlank())
                throw new Exception();
            var user = userRepository.findUserByUserName(userName);
            userResponse = this.setResponseResult(user);
            ResponseCookie cookie = ResponseCookie.from("accessToken", userResponse.getToken())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(3600)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }catch (Exception e){
            userResponse.setStatusCode(500);
            userResponse.setMessage("Something wrong when get new session");
            userResponse.setError(e.getMessage());
        }
        return userResponse;
    }

    @Override
    public UserAuthenRequest signInAdmin(LoginRequest signInRequest, HttpServletResponse response) {
        UserAuthenRequest userResponse = new UserAuthenRequest();
        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUserName(),signInRequest.getPassword()));
            var user = userRepository.findUserByUserName(signInRequest.getUserName());
            if(!user.getRole().equals("admin")){
                userResponse.setStatusCode(403);
                userResponse.setMessage("Something wrong when login");
                userResponse.setError("User is not admin");
                return userResponse;
            }
            userResponse = this.setResponseResult(user);
            ResponseCookie cookie = ResponseCookie.from("accessToken", userResponse.getToken())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(3600)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }catch (Exception e){
            userResponse.setStatusCode(500);
            userResponse.setMessage("Something wrong when login");
            userResponse.setError(e.toString());
        }
        return userResponse;
    }

    @Override
    public User getUserToken(String token) {
        String username = this.jwtHelper.extractUserName(token);
        return this.userRepository.findUserByUserName(username);
    }

    private UserAuthenRequest setResponseResult(User user){
        var result = new UserAuthenRequest();
        var jwt = jwtHelper.generateToken(user);
        var refreshToken = jwtHelper.generateRefreshToken(new HashMap<>(), user);
        result.setStatusCode(200);
        result.setToken(jwt);
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setRole(user.getRole());
        result.setUserId(user.getUserId());
        result.setUserName(user.getName());
        result.setRefreshToken(refreshToken);
        result.setExpirationTime("1 hour");
        result.setMessage("Sign In successful");
        return result;
    }
}
