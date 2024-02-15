package com.example.codeE.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.codeE.model.login.UserLogin;
import com.example.codeE.service.user.UserImpl;

@RestController
@RequestMapping("/access")
public class Login {

    @Autowired
    private UserImpl userImplement;
    
    @PostMapping
    @RequestMapping("/login")
    public void LoginApi(UserLogin userLogin) {
        
    }

    @PostMapping    
    @RequestMapping("/logout")
    public void LogoutApi() {
    }

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello");
    }
    
    @GetMapping("/goodbye")
    public ResponseEntity<String> sayGoodbye(){
        return ResponseEntity.ok("Goodbye");
    }
}
