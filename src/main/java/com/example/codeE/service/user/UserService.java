package com.example.codeE.service.user;

import com.example.codeE.model.user.User;
import com.example.codeE.request.user.CreateUserRequest;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.request.user.UpdateUserRequest;
import com.example.codeE.service.common.CommonService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService extends CommonService<User, CreateUserRequest> {
    List<User> getUsersByRoleAndSearchKeyword(GetUsersRequest getUsersRequest);
    List<User> paginateUsers(GetUsersRequest getUsersRequest);
    boolean saveUserToDatabase(MultipartFile file);
    // boolean exportExcel();
    User updateById(String userId, UpdateUserRequest updatedUser);
}
