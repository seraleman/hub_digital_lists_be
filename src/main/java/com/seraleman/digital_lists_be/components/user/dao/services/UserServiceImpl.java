package com.seraleman.digital_lists_be.components.user.dao.services;

import java.util.ArrayList;
import java.util.List;

import com.seraleman.digital_lists_be.components.user.User;
import com.seraleman.digital_lists_be.components.user.dao.IUserDao;
import com.seraleman.digital_lists_be.services.IResponseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    IResponseService response;

    @Override
    public ResponseEntity<?> list() {
        List<User> users = new ArrayList<>();

        try {
            users = (List<User>) userDao.findAll();
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }

        if (users.size() == 0) {
            return response.empty();
        }
        return response.list(users);
    }

    @Override
    public ResponseEntity<?> show(Long id) {
        User user = null;

        try {
            user = userDao.findById(id).orElse(null);
        } catch (DataAccessException e) {
            response.errorDataAccess(e);
        }

        if (user == null) {
            return response.notFound(id);
        }

        return response.found(user);
    }

    @Override
    public ResponseEntity<?> create(User user) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Long id, User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
