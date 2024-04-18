package com.example.codeE.service.user;

import com.example.codeE.model.user.User;
import com.example.codeE.request.group.GroupTopicResponse;
import com.example.codeE.request.user.CreateUserRequest;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.request.user.UpdateUserRequest;
import com.example.codeE.service.common.CommonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService extends CommonService<User, CreateUserRequest> {
    List<User> getUsersByRoleOrAll(String role);
    List<User> getUsersByRoleAndSearchKeyword(GetUsersRequest getUsersRequest);
    List<User> paginateUsers(GetUsersRequest getUsersRequest);
    ResponseEntity<Map<String, String>> saveUserToDatabase(MultipartFile file);
    // boolean exportExcel();
    User updateById(String userId, UpdateUserRequest updatedUser);
    User getUserByUserName(String role, String userName);
    User ChangePassword(String userId, String newPassword, String oldPassword);

    List<GroupTopicResponse> getAllGroupsByUserId(String userId);

    User getUserByUserId(String userId);
}
