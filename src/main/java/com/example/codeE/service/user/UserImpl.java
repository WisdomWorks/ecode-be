package com.example.codeE.service.user;

import com.example.codeE.mapper.user.UserFromExcel;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.request.user.UpdateUserRequest;
import com.example.codeE.security.BCryptPassword;
import com.example.codeE.helper.ExcelHelper;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getById(String userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        return userOptional.orElse(null);
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
    public boolean deleteById(@NotBlank String userId) {
        if(!userRepository.existsById(userId)){
            return false;
        }
        this.userRepository.deleteById(userId);
        return true;
    }

    @Override
    public List<User> paginateUsers(GetUsersRequest getUsersRequest) {
        return this.userRepository.findUsersByRoleAndSearchKeywordWithPagination(
                getUsersRequest.getRole(),
                getUsersRequest.getSearchKeyword(),
                getUsersRequest.getPageable());
    }

    @Override
    public boolean saveUserToDatabase(MultipartFile file) {
        if (ExcelHelper.isValidExcelFile(file)) {
            try {
                List<User> users = new ArrayList<>();
                List<UserFromExcel> importedUsers = ExcelHelper.importFromExcel(file.getInputStream(), UserFromExcel.class);
                for (UserFromExcel excelUser : importedUsers) {
                    excelUser.setRole(excelUser.getRole().toLowerCase());
                    users.add(new User(excelUser));
                }
                for(User user : users){
                    System.out.println(user.toString());
                }
                this.userRepository.saveAll(users);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean exportExcel() {
        // List<User> users = (List<User>) this.userRepository.findAll();
        // if (users.size() > 0) {
        //     ExcelHelper.writeExcel(users, "Users", user);
        //     return true;
        // }
        return false;
    }
}
