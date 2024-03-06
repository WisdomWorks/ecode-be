package com.example.codeE.controller;

import com.example.codeE.request.user.UserAuthenRequest;
import com.example.codeE.service.authentication.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    private AuthenService authenService;

    @PostMapping
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@RequestBody UserAuthenRequest signInRequest){
        return ResponseEntity.ok(authenService.signIn(signInRequest));
    }
}
