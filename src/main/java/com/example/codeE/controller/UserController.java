package com.example.codeE.controller;

import com.example.codeE.model.User;
import com.example.codeE.service.implement.UserImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserImplement userImplement;

    @GetMapping
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers(){
        List<User> listaPersona = this.userImplement.getAllUsers();
        return ResponseEntity.ok(listaPersona);
    }

    @PostMapping
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user){
        User personaCreada = this.userImplement.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaCreada);
    }

    @PutMapping
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User updatedUser = this.userImplement.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
    }

    @GetMapping
    @RequestMapping(value = "{userId}",method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable String userId){
        User persona = this.userImplement.getUser(userId);
        return ResponseEntity.ok(persona);
    }


    @DeleteMapping
    @RequestMapping(value = "{userId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        this.userImplement.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
