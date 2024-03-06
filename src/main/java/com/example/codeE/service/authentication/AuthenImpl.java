package com.example.codeE.service.authentication;

import com.example.codeE.helper.JWTUtils;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.user.UserAuthenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenImpl implements  AuthenService{
    @Autowired
    private UserRepository userRepository;
    JWTUtils jwtHelper = new JWTUtils();
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public UserAuthenRequest signIn(UserAuthenRequest signInRequest) {
        UserAuthenRequest response = new UserAuthenRequest();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUserName(),signInRequest.getPassword()));
            System.out.println(signInRequest.getUserName()+","+signInRequest.getPassword());
            var user = userRepository.findUserByUserName(signInRequest.getUserName());
            System.out.println(user);
            var jwt = jwtHelper.generateToken(user);
            var refreshToken = jwtHelper.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("1h");
            response.setMessage("Sign In successful");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }
}
