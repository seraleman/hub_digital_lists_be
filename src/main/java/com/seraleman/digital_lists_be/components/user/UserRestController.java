package com.seraleman.digital_lists_be.components.user;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import com.google.zxing.WriterException;
import com.seraleman.digital_lists_be.components.user.helpers.service.IUserService;
import com.seraleman.digital_lists_be.helpers.localDateTime.ILocalDateTime;
import com.seraleman.digital_lists_be.helpers.qr.QRCodeGenerator;
import com.seraleman.digital_lists_be.helpers.response.IResponse;

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
@RequestMapping("/digitalLists/user")
public class UserRestController {

    private final String imagePath = "./src/main/resources/qrCodes/QRCode.png";

    @Autowired
    private IUserService userService;

    @Autowired
    IResponse response;

    @Autowired
    private ILocalDateTime localDateTime;

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {

        try {
            List<User> users = userService.getUsers();
            if (users.size() == 0) {
                return response.empty();
            }
            return response.list(users);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @GetMapping("/byReason/{reasonId}")
    public ResponseEntity<?> getUsersByReasonId(@PathVariable Long reasonId) {
        try {
            List<User> users = userService.getUsersByReasonId(reasonId);
            if (users.size() == 0) {
                return response.empty();
            }
            return response.list(users);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) throws WriterException, IOException {

        try {
            User user = userService.getUserById(id);
            if (user == null) {
                return response.notFound(id);
            }
            QRCodeGenerator.generateQRCodeImage(
                    "localhost:8080/digitalLists/user/".concat(String.valueOf(id)),
                    250, 250,
                    imagePath);

            return response.found(user);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result)
            throws WriterException, IOException {

        if (result.hasErrors()) {
            return response.invalidObject(result);
        }
        try {
            user.setCreated(localDateTime.getLocalDateTime());
            QRCodeGenerator.generateQRCodeImage("Probando hoy 07/05/2022", 250, 250, imagePath);
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
            userCurrent.setReason(user.getReason());
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
