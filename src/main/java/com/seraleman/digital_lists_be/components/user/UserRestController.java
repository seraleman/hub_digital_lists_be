package com.seraleman.digital_lists_be.components.user;

import java.util.List;

import javax.validation.Valid;

import com.seraleman.digital_lists_be.components.user.services.IUserService;
import com.seraleman.digital_lists_be.services.IResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("digitalLists/user")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    IResponse response;

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {

        try {
            List<User> users = userService.getAllUsers();
            if (users.size() == 0) {
                return response.empty();
            }
            return response.list(users);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @GetMapping("/byHappening/{happening}")
    public ResponseEntity<?> getAllUsersByHappening(@PathVariable String happening) {
        try {
            List<User> users = userService.getAllUsersByHappening(happening);
            if (users.size() == 0) {
                return response.empty();
            }
            return response.list(users);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {

        try {
            User user = userService.getUserById(id);
            if (user == null) {
                return response.notFound(id);
            }
            return response.found(user);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {

        if (result.hasErrors()) {
            return response.invalidObject(result);
        }
        try {
            return response.created(userService.saveUser(user));
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @Valid @RequestBody User user,
            BindingResult result) {

        if (result.hasErrors()) {
            return response.invalidObject(result);
        }
        try {
            User userCurrent = userService.getUserById(id);
            if (userCurrent == null) {
                return response.notFound(id);
            }
            userCurrent.setDocumentNumber(user.getDocumentNumber());
            userCurrent.setDocumentType(user.getDocumentType());
            userCurrent.setEmail(user.getEmail());
            userCurrent.setFullName(user.getFullName());
            userCurrent.setHappening(user.getHappening());
            userService.saveUser(userCurrent);
            return response.updated(userCurrent);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {

        try {

            User user = userService.getUserById(id);
            if (user == null) {
                return response.notFound(id);
            }
            userService.deleteUserById(id);
            return response.deleted();
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }
}
