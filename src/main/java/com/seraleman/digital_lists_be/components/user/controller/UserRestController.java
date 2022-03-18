package com.seraleman.digital_lists_be.components.user.controller;

import com.seraleman.digital_lists_be.components.user.User;
import com.seraleman.digital_lists_be.components.user.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("DigitalLists/users")
public class UserRestController implements IUserRestController {

    @Autowired
    private IUserService userService;

    @Override
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<?> createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, User user) {
        return userService.updateUserById(id, user);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }
}
