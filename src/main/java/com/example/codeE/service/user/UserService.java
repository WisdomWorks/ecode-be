package com.example.codeE.service.user;

import com.example.codeE.model.user.User;
import com.example.codeE.request.user.PaginationRequest;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User createUser(User user);
    public User updateUser(User user);
    public void deleteUser(String userId);
    public User getUser(String userId);
    public List<User> paginateUsers(PaginationRequest paginationRequest);
}