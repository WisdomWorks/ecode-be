package com.example.codeE.controller;

import com.example.codeE.model.common.Pagination;
import com.example.codeE.model.user.User;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.service.user.UserImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserImpl userImplement;

    @GetMapping
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers(@Valid @ModelAttribute GetUsersRequest getUsersRequest
    ){
        int totalRecords = this.userImplement.getUsersByRoleAndSearchKeyword(getUsersRequest).size();
        List<User> listUsers = this.userImplement.paginateUsers(getUsersRequest);
        return new ResponseEntity<>(
                Map.of("users", listUsers,
                        "pagination", new Pagination(
                                totalRecords,
                                getUsersRequest.getPageSize(),
                                getUsersRequest.getPageNumber(),
                                (int) Math.ceil((double) totalRecords / getUsersRequest.getPageSize())
                        )), HttpStatus.OK);
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
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        User persona = this.userImplement.getUser(userId);
        return ResponseEntity.ok(persona);
    }
}
