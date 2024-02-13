package com.example.codeE.service.user;

import com.example.codeE.model.user.User;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.request.user.UpdateUserRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    List<User> getUsersByRoleAndSearchKeyword(GetUsersRequest getUsersRequest);
    User createUser(User user);
    User updateById(String userId, UpdateUserRequest updatedUser);
    void deleteById(String userId);
    User getById(String userId);
    List<User> paginateUsers(GetUsersRequest getUsersRequest);
    void saveUserToDatabase(MultipartFile file);
}
