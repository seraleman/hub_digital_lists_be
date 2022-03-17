package com.seraleman.digital_lists_be.components.user.dao.services;

import com.seraleman.digital_lists_be.components.user.User;

import org.springframework.http.ResponseEntity;

public interface IUserService {

    public ResponseEntity<?> list();

    public ResponseEntity<?> show(Long id);

    public ResponseEntity<?> create(User user);

    public ResponseEntity<?> update(Long id, User user);

    public ResponseEntity<?> deleteById(Long id);

}
