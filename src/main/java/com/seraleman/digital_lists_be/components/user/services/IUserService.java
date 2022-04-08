package com.seraleman.digital_lists_be.components.user.services;

import java.util.List;

import com.seraleman.digital_lists_be.components.user.User;

public interface IUserService {

    List<User> getAllUsers();

    List<User> getAllUsersByHappening(String happening);

    User getUserById(Long id);

    User saveUser(User user);

    void deleteUserById(Long id);

}
