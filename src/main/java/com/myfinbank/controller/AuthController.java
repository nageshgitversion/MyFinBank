package com.myfinbank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myfinbank.model.User;
import com.myfinbank.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:58837")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> validateUser(@RequestBody User user) {
    	Optional<User> userResponse = userService.validateUser(user);
    	if (userResponse.isPresent()) {
            User authenticatedUser = userResponse.get();
            return ResponseEntity.ok(authenticatedUser);  // Return the user object with status 200 OK
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");  // Return status 401 Unauthorized
        }
    }
   
    
}
