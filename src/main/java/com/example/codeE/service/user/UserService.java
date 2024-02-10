package com.example.codeE.service.user;

import com.example.codeE.model.user.User;
import com.example.codeE.request.user.GetUsersRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    List<User> getUsersByRoleAndSearchKeyword(GetUsersRequest getUsersRequest);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(String userId);
    User getUserById(String userId);
    User getUserByUserName(String userName);
    List<User> paginateUsers(GetUsersRequest getUsersRequest);
    void saveUserToDatabase(MultipartFile file);
}
