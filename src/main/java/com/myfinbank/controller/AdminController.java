package com.myfinbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myfinbank.model.Account;
import com.myfinbank.model.User;
import com.myfinbank.service.AccountService;
import com.myfinbank.service.UserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:58837")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/users/deactivate")
    public User deactivateUser(@RequestParam Long userId) {
        return userService.deactivateUser(userId);
    }

    @PostMapping("/users/activate")
    public User activateUser(@RequestParam Long userId) {
        return userService.activateUser(userId);
    }

    @PostMapping("/accounts/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountService.findAll();
    }
    
    @GetMapping("/users")
    public List<User> getUserss() {
        return userService.findAll();
    }

    @PutMapping("/accounts/update")
    public Account updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }
    
    @GetMapping("/user")
    public ResponseEntity<List<Account>> getAccountsByUserId(@RequestParam Long userId) {
        List<Account> accounts = accountService.getAccountsByUserId(userId);
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/accounts/delete")
    public void deleteAccount(@RequestParam Long accountId) {
        accountService.deleteAccount(accountId);
    }
}
