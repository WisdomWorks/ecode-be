package com.example.codeE.controller;

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
import org.springframework.web.server.ResponseStatusException;

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
        var apiResponse = new ApiResponse();
        try{
            apiResponse.setStatus(200);
            apiResponse.setMessage("Create user successful");
            apiResponse.setValue(this.userService.createOne(user))
        }catch(Exception e){
            apiResponse.setStatus(500);
            apiResponse.setMessage("Something wrong when create new user");
            apiResponse.setError(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<?> getUsersByRoleOrAll(@Valid @RequestParam(required = false) String role){
        var apiResponse = new ApiResponse();
        try{
            apiResponse.setStatus(200);
            apiResponse.setMessage("Get users successful");
            apiResponse.setValues(this.userService.getUsersByRoleOrAll(role))
        }catch(Exception e){
            apiResponse.setStatus(500);
            apiResponse.setError(e.getMessage());
            apiResponse.setMessage("Something wrong when get all users");
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PatchMapping
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateById(@Valid @RequestBody UpdateUserRequest updatedUser) {
        var apiResponse = new ApiResponse();
        try{
            apiResponse.setStatus(200);
            apiResponse.setMessage("Get users successful");
            apiResponse.setValues(this.userService.getUsersByRoleOrAll(role))
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }catch(NoSuchElementException e){
            apiResponse.setStatus(404);
            apiResponse.setError(e.getMessage());
            apiResponse.setMessage("Something wrong when update all users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse); 
        }
        }catch (Exception e){
            apiResponse.setStatus(500);
            apiResponse.setError(e.getMessage());
            apiResponse.setMessage("Something wrong when update all users");
        }
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
