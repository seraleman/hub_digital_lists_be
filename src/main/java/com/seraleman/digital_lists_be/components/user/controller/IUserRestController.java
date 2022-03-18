package com.seraleman.digital_lists_be.components.user.controller;

import com.seraleman.digital_lists_be.components.user.User;

import org.springframework.http.ResponseEntity;

public interface IUserRestController {

    public ResponseEntity<?> getAllUsers();

    public ResponseEntity<?> getUserById(Long id);

    public ResponseEntity<?> createUser(User user);

    public ResponseEntity<?> updateUserById(Long id, User user);

    public ResponseEntity<?> deleteUserById(Long id);

}
