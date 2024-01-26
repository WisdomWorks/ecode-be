package com.example.codeE.service.implement;

import com.example.codeE.model.User;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserImplement implements UserService {
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
}
