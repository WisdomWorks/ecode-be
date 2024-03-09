package com.example.codeE.controller;

import com.example.codeE.request.user.LoginRequest;
import com.example.codeE.service.authentication.AuthenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    private AuthenService authenService;

    @PostMapping
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@RequestBody LoginRequest signInRequest, HttpServletResponse response){
        var result = authenService.signIn(signInRequest, response);
        if(result.getStatusCode() == 200){
            return ResponseEntity.ok(result);
        }
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
    @PostMapping
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public  ResponseEntity<?> Logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextHolder.clearContext();
        clearAuthenticationTokens(request, response);
        return ResponseEntity.status(HttpStatus.OK).body("Logout Successfully");
    }
    @GetMapping
    @RequestMapping(value = "/check-session", method = RequestMethod.GET)
    public ResponseEntity<?> checkSession(HttpServletRequest request, HttpServletResponse response){
        String token = "";
        int age = 0;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    age = cookie.getMaxAge();
                }
            }
        }
        return ResponseEntity.ok(Map.of("token" , token, "age", age));
    }

    private void clearAuthenticationTokens(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }
}
