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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private static final Logger logger = LoggerFactory.getLogger(UserImpl.class);

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
        if (role == null || role.equals("all")) {
            return this.userRepository.findAll();
        } else if (!VALID_ROLES.contains(role)) {
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
    public ResponseEntity<Map<String, String>> saveUserToDatabase(MultipartFile file) {
        String passwordHash = BCryptPassword.generateRandomPassword();
        Map<String, String> response = new HashMap<>();
        if (ExcelHelper.isValidExcelFile(file)) {
            try {
                List<User> users = new ArrayList<>();
                List<String> unsuccessfulUsers = new ArrayList<>();
                List<UserFromExcel> importedUsers = ExcelHelper.importFromExcel(file.getInputStream(), UserFromExcel.class);
                for (UserFromExcel excelUser : importedUsers) {
                    try {
                        excelUser.setRole(excelUser.getRole().toLowerCase());
                        users.add(new User(excelUser, BCryptPassword.passwordEncoder(passwordHash)));
                        userRepository.save(users.get(users.size() - 1));
                    } catch (Exception ex) {
                        unsuccessfulUsers.add(excelUser.getUsername());
                        logger.error("Error saving user to database", ex);
                    }
                }
                response.put("message", "Users saved successfully");
                response.put("unsuccessfulUsers", unsuccessfulUsers.toString());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (IOException e) {
                logger.error("Error processing the file", e);
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        response.put("message", "Invalid file format. Please upload a valid excel file");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
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
