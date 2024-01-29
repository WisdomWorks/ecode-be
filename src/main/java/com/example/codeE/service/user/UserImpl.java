package com.example.codeE.service.user;

import com.example.codeE.model.user.User;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.user.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        this.userRepository.deleteById(userId);
    }
    @Override
    public User getUser(String userId) {
        return this.userRepository.findById(userId).get();
    }
    @Override
    public List<User> paginateUsers(PaginationRequest paginationRequest) {
        return this.userRepository.findUsersByRoleAndSearchKeyword(
                paginationRequest.getRole(),
                paginationRequest.getSearchKeyword(),
                paginationRequest.getPageable());
    }
}
