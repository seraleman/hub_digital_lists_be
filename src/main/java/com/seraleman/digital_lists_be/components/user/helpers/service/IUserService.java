package com.seraleman.digital_lists_be.components.user.helpers.service;

import java.util.List;

import com.seraleman.digital_lists_be.components.user.User;

public interface IUserService {

    List<User> getUsers();

    List<User> getUsersByReasonId(Long id);

    User getUserByDocumentNumber(Integer userDocumentNumber);

    User getUserById(Long id);

    User saveUser(User user);

    void deleteUserById(Long id);

}
