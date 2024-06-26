package com.example.codeE.controller;

import com.example.codeE.request.user.ChangePasswordRequest;
import com.example.codeE.request.user.CreateUserRequest;
import com.example.codeE.request.user.UpdateUserRequest;
import com.example.codeE.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping
//    @RequestMapping(value = "",method = RequestMethod.GET)
//    public ResponseEntity<?> getAllUsers(@Valid @ModelAttribute GetUsersRequest getUsersRequest) {
//        int totalRecords = this.userService.getUsersByRoleAndSearchKeyword(getUsersRequest).size();
//        getUsersRequest.setPageable(PageRequest.of(getUsersRequest.getPageNumber()-1, getUsersRequest.getPageSize()));
//        List<User> listUsers = this.userService.paginateUsers(getUsersRequest);
//        return new ResponseEntity<>(
//                Map.of("users", listUsers,
//                        "pagination", new Pagination(
//                                totalRecords,
//                                getUsersRequest.getPageSize(),
//                                getUsersRequest.getPageNumber(),
//                                (int) Math.ceil((double) totalRecords / getUsersRequest.getPageSize())
//                        )), HttpStatus.OK);
//    }

    @PostMapping
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest user){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.createOne(user));
    }

    @GetMapping
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<?> getUsersByRoleOrAll(@Valid @RequestParam(required = false) String role){
        return ResponseEntity.ok(this.userService.getUsersByRoleOrAll(role));
    }

    @PatchMapping
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateById(@Valid @RequestBody UpdateUserRequest updatedUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.updateById(updatedUser.getUserId(), updatedUser));
    }

    @GetMapping
    @RequestMapping(value = "{userId}",method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String userId) {
        return ResponseEntity.ok(this.userService.getById(userId));
    }

    @PostMapping
    @RequestMapping(value = "/import-users",method = RequestMethod.POST)
    public ResponseEntity<?> importUsersByExcel(@Valid @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "File is empty"));
        }

        return this.userService.saveUserToDatabase(file);
    }

    @DeleteMapping
    @RequestMapping(value = "{userId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@Valid @PathVariable String userId){
        this.userService.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","Delete user successful!"));
    }
    @GetMapping
    @RequestMapping(value = "get-by-user-name/{username}", method = RequestMethod.GET)
    public  ResponseEntity<?> getUserByUserName (@PathVariable String username,String role){
        var result = this.userService.getUserByUserName(role, username);
        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","No user found with user name:" + username));
        }
        return  ResponseEntity.ok(result);
    }
    @PutMapping
    @RequestMapping(value = "change-password", method = RequestMethod.PUT)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.ChangePassword(request.getUserId(), request.getNewPassword(), request.getOldPassword()));
    }
}