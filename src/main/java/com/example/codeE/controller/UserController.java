package com.example.codeE.controller;

import com.example.codeE.model.common.Pagination;
import com.example.codeE.model.user.User;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.request.user.UpdateUserRequest;
import com.example.codeE.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers(@Valid @ModelAttribute GetUsersRequest getUsersRequest) {
        int totalRecords = this.userService.getUsersByRoleAndSearchKeyword(getUsersRequest).size();
        getUsersRequest.setPageable(PageRequest.of(getUsersRequest.getPageNumber()-1, getUsersRequest.getPageSize()));
        List<User> listUsers = this.userService.paginateUsers(getUsersRequest);
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
        User createdUser = this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PatchMapping
    @RequestMapping(value = "{userId}",method = RequestMethod.PATCH)
    public ResponseEntity<?> updateById(@PathVariable String userId,@Valid @RequestBody UpdateUserRequest updatedUser){
        User user = this.userService.updateById(userId, updatedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    @RequestMapping(value = "{userId}",method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String userId) {
        User user = this.userService.getById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @RequestMapping(value = "/import-users",method = RequestMethod.POST)
    public ResponseEntity<?> importUsersByExcel(@RequestParam("file") MultipartFile file) {
        this.userService.saveUserToDatabase(file);
        return ResponseEntity.ok(Map.of("message" , " Users data uploaded and saved to database successfully"));
    }

    @DeleteMapping
    @RequestMapping(value = "{userId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable String userId){
        User user = this.userService.getById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        this.userService.deleteById(userId);
        return ResponseEntity.ok(Map.of("message" , "Users is deleted successfully"));
    }
}
