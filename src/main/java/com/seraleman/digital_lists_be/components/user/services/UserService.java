package com.seraleman.digital_lists_be.components.user.services;

import java.util.List;

import com.seraleman.digital_lists_be.components.user.IUserDao;
import com.seraleman.digital_lists_be.components.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userDao.findAll();
    }

    @Override
    public List<User> getAllUsersByHappening(String happening) {
        return userDao.findAllByhappening(happening);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

}