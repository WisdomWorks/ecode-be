package com.example.codeE.controller;

import com.example.codeE.request.user.LoginRequest;
import com.example.codeE.service.authentication.AuthenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    private void clearAuthenticationTokens(HttpServletRequest request, HttpServletResponse response) {
        // Clear any tokens or cookies used for authentication
        // For example, remove JWT token from the client side

        // Assuming you are using JWT as a token in a cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("your_jwt_cookie_name".equals(cookie.getName())) {
                    // Expire the cookie by setting its maxAge to 0
                    cookie.setMaxAge(0);
                    // Optionally, clear the cookie path and domain
                    cookie.setPath("/");
                    cookie.setDomain("yourdomain.com");
                    response.addCookie(cookie);
                }
            }
        }
    }
}
