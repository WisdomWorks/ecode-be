package com.example.codeE.service.user;

import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.mapper.user.UserFromExcel;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.request.user.UpdateUserRequest;
import com.example.codeE.security.BCryptPassword;
import com.example.codeE.helper.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getById(String userId) {
        return this.userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRoleAndSearchKeyword(GetUsersRequest getUsersRequest) {
        return (List<User>) this.userRepository.findUsersByRoleAndSearchKeyword(
                getUsersRequest.getRole(),
                getUsersRequest.getSearchKeyword()
        );
    }

    @Override
    public User createOne(User user) {
        String password = BCryptPassword.generateRandomPassword();
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(password);
        return this.userRepository.save(user);
    }


    @Override
    public User updateById(String userId, UpdateUserRequest updatedUser) {
        User existingUser = userRepository.findById(userId).get();

        if (updatedUser.getUpdatedName() != null) {
            existingUser.setName(updatedUser.getUpdatedName());
        }
        if (updatedUser.getUpdatedEmail() != null) {
            existingUser.setEmail(updatedUser.getUpdatedEmail());
        }
        if (updatedUser.getUpdatedUsername() != null) {
            existingUser.setUsername(updatedUser.getUpdatedUsername());
        }
        if (updatedUser.getUpdatedPassword() != null){
            existingUser.setPassword(updatedUser.getUpdatedPassword());
        }
        if (updatedUser.getUpdatedRole() != null) {
            existingUser.setRole(updatedUser.getUpdatedRole());
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteById(String userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public List<User> paginateUsers(GetUsersRequest getUsersRequest) {
        return this.userRepository.findUsersByRoleAndSearchKeywordWithPagination(
                getUsersRequest.getRole(),
                getUsersRequest.getSearchKeyword(),
                getUsersRequest.getPageable());
    }

    @Override
    public void saveUserToDatabase(MultipartFile file) {
        if (ExcelHelper.isValidExcelFile(file)) {
            try {
                List<User> users = new ArrayList<>();
                List<UserFromExcel> importedUsers = ExcelHelper.importFromExcel(file.getInputStream(), UserFromExcel.class);
                for (UserFromExcel excelUser : importedUsers) {
                    users.add(new User(excelUser));
                }
                for(User user : users){
                    System.out.println(user.toString());
                }
                this.userRepository.saveAll(users);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }
}
