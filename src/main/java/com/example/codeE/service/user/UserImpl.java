package com.example.codeE.service.user;

import com.example.codeE.mapper.user.UserFromExcel;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.user.CreateUserRequest;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.request.user.UpdateUserRequest;
import com.example.codeE.helper.ExcelHelper;
import com.example.codeE.security.BCryptPassword;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static com.example.codeE.constant.Constant.VALID_ROLES;


@Service
public class UserImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getById(@NotBlank String userId) {;
        return this.userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No user found with ID:" + userId));
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRoleOrAll(String role){
        if (!VALID_ROLES.contains(role)) {
            throw new NoSuchElementException("Role not found");
        }
        return this.userRepository.findUsersByRole(role);
    }

    @Override
    public List<User> getUsersByRoleAndSearchKeyword(GetUsersRequest getUsersRequest) {
        return (List<User>) this.userRepository.findUsersByRoleAndSearchKeyword(
                getUsersRequest.getRole(),
                getUsersRequest.getSearchKeyword()
        );
    }

    @Override
    public User createOne(CreateUserRequest userRequest) {
        String passwordString = BCryptPassword.generateRandomPassword();
        var user = new User(userRequest, UUID.randomUUID().toString(), BCryptPassword.passwordEncoder(passwordString));
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
    public User getUserByUserName(String role, String userName) {
        return this.userRepository.findUserByUserName(userName);
//        return this.userRepository.findUserByRoleAndUserName(role, userName);
    }


    @Override
    public void deleteById(@NotBlank String userId) {
        if(userRepository.existsById(userId)){
            this.userRepository.deleteById(userId);
        }else {
            throw new NoSuchElementException("User not found with id " + userId);
        }
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
        String passwordHash = BCryptPassword.generateRandomPassword();
        if (ExcelHelper.isValidExcelFile(file)) {
            try {
                List<User> users = new ArrayList<>();
                List<UserFromExcel> importedUsers = ExcelHelper.importFromExcel(file.getInputStream(), UserFromExcel.class);
                for (UserFromExcel excelUser : importedUsers) {
                    excelUser.setRole(excelUser.getRole().toLowerCase());
                    users.add(new User(excelUser, BCryptPassword.passwordEncoder(passwordHash)));
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByUserName(username);
    }

    // @Override
    // public boolean exportExcel() {
    //     // List<User> users = (List<User>) this.userRepository.findAll();
    //     // if (users.size() > 0) {
    //     //     ExcelHelper.writeExcel(users, "Users", user);
    //     //     return true;
    //     // }
    //     return false;
    // }
}
