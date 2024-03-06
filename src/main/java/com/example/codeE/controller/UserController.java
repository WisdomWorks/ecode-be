package com.example.codeE.controller;

import com.example.codeE.model.common.Pagination;
import com.example.codeE.model.user.User;
import com.example.codeE.request.user.CreateUserRequest;
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
    @RequestMapping(value = "pagination",method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsersPagination(@Valid @ModelAttribute GetUsersRequest getUsersRequest) {
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
    @GetMapping
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?>getAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.getAll());
    }
    @PostMapping
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest user){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createOne(user));
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
    public ResponseEntity<?> importUsersByExcel(@RequestParam("file") MultipartFile file) {
        boolean result = this.userService.saveUserToDatabase(file);
        if(!result){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to import users");
        }
        return ResponseEntity.ok(Map.of("message" , " Users data uploaded and saved to database successfully"));
    }

    @DeleteMapping
    @RequestMapping(value = "{userId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable String userId){
        boolean result = this.userService.deleteById(userId);
        if(!result){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with ID:" + userId);
        }
        return ResponseEntity.ok(Map.of("message" , "User is deleted successfully"));
    }
    @GetMapping
    @RequestMapping(value = "get-by-user-name/{username}", method = RequestMethod.GET)
    public  ResponseEntity<?> getUserByUserName (@PathVariable String username,String role){
        var result = this.userService.getUserByUserName(role, username);
        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with user name:" + username);
        }
        return  ResponseEntity.ok(result);
    }
}
