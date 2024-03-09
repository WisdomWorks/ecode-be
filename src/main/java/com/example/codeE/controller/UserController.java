package com.example.codeE.controller;

import com.example.codeE.model.user.User;
import com.example.codeE.request.ApiResponse;
import com.example.codeE.request.user.CreateUserRequest;
import com.example.codeE.request.user.UpdateUserRequest;
import com.example.codeE.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.annotation.Validated;

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
    public ApiResponse<User> createUser(@RequestBody CreateUserRequest user){
        var response = new ApiResponse<User>();
        try{
            var userCreated = this.userService.createOne(user);
            response.setMessage("Create User Successful");
            response.setStatus(200);
            response.setValue(userCreated);
        }catch (Exception e){
            response.setMessage("Can not create user");
            response.setStatus(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    @GetMapping
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ApiResponse<User> getUsersByRoleOrAll(@Valid @RequestParam(required = false) String role){
        var response = new ApiResponse<User>();
        var userList = this.userService.getUsersByRoleOrAll(role);
        response.setValues(userList);
        if (!userList.isEmpty()){
            response.setMessage("No content");
            response.setStatus(204);
        }else {
            response.setMessage("Get user successful");
            response.setStatus(200);
        }
        return response;
    }

    @PatchMapping
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public ApiResponse<User> updateById(@Valid @RequestBody UpdateUserRequest updatedUser) {
        var response = new ApiResponse<User>();
        try{
            var user = this.userService.updateById(updatedUser.getUserId(), updatedUser);
            response.setMessage("Update User successful");
            response.setValue(this.userService.updateById(updatedUser.getUserId(), updatedUser));
            response.setStatus(200);
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Can not update user");
            response.setError(e.getMessage());
        }
        return response;
    }

    @GetMapping
    @RequestMapping(value = "{userId}",method = RequestMethod.GET)
    public ApiResponse<User> getById(@PathVariable String userId) {
        var response = new ApiResponse<User>();
        try {
            response.setStatus(200);
            response.setMessage("Get User successful");
            response.setValue(this.userService.getById(userId));
        }catch (Exception e){
            response.setStatus(500);
            response.setError(e.getMessage());
            response.setMessage("Can not get user by this id: "+ userId);
        }
        return response;
    }

    @PostMapping
    @RequestMapping(value = "/import-users",method = RequestMethod.POST)
    public ResponseEntity<?> importUsersByExcel(@Valid @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "File is empty"));
        }
        ResponseEntity<Map<String, String>> result = this.userService.saveUserToDatabase(file);
        return result;
    }

    @DeleteMapping
    @RequestMapping(value = "{userId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@Valid @PathVariable String userId){
        this.userService.deleteById(userId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with ID:" + userId);
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
