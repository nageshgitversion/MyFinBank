package com.myfinbank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinbank.Repository.UserRepository;
import com.myfinbank.model.Account;
import com.myfinbank.model.Role;
import com.myfinbank.model.User;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository; 
    
    public User registerUser(User user) {
        user.setPassword(user.getPassword());
        if (user.getRole() == Role.USER) 
        	user.setRole(Role.USER);
        else
        	user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User deactivateUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        return userRepository.save(user);
    }

    public User activateUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(true);
        return userRepository.save(user);
    }

    public Optional<User> validateUser(User user) {
        Optional<User> userResponse = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return userResponse;
    }
    
    
}
