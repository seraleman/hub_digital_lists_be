package com.seraleman.digital_lists_be.components.user.services;

import java.util.ArrayList;
import java.util.List;

import com.seraleman.digital_lists_be.components.user.IUserDao;
import com.seraleman.digital_lists_be.components.user.User;
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
    public ResponseEntity<?> getAllUsers() {

        try {
            List<User> users = new ArrayList<>();
            users = (List<User>) userDao.findAll();
            if (users.size() == 0) {
                return response.empty();
            }
            return response.list(users);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {

        try {
            User user = userDao.findById(id).orElse(null);
            if (user == null) {
                return response.notFound(id);
            }
            return response.found(user);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @Override
    public ResponseEntity<?> createUser(User user) {

        try {
            User userCreated = userDao.save(user);
            return response.created(userCreated);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @Override
    public ResponseEntity<?> updateUserById(Long id, User user) {

        try {
            User userCurrent = userDao.findById(id).orElse(null);
            if (userCurrent == null) {
                return response.notFound(id);
            }
            userCurrent.setDocumentNumber(user.getDocumentNumber());
            userCurrent.setDocumentType(user.getDocumentType());
            userCurrent.setEmail(user.getEmail());
            userCurrent.setFullName(user.getFullName());
            userCurrent.setHappening(user.getHappening());
            userDao.save(userCurrent);
            return response.updated(userCurrent);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @Override
    public ResponseEntity<?> deleteUserById(Long id) {

        try {
            User userCurrent = null;
            userCurrent = userDao.findById(id).orElse(null);
            if (userCurrent == null) {
                return response.notFound(id);
            }
            userDao.deleteById(id);
            return response.deleted();
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }
}
