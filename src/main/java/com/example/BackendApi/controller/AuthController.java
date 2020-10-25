package com.example.BackendApi.controller;

import com.example.BackendApi.entity.User;
import com.example.BackendApi.repository.RoleName;
import com.example.BackendApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    User loggedUser;

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<String> login(@PathVariable String username, @PathVariable String password) {
        try {
            User user = userService.findUserByUsername(username);
            loggedUser = user;
            if (user.getPassword().equals(password)) {
                return new ResponseEntity<>("Successfully logged", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Wrong username or password", HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/resetPassword/{username}")
    public ResponseEntity<String> resetPassword(@PathVariable String username) {
        try {
            User user = userService.findUserByUsername(username);
            byte[] array = new byte[7];
            new Random().nextBytes(array);
            String generatedPassword = new String(array, Charset.forName("UTF-8"));
            user.setPassword(generatedPassword);
            userService.saveUser(user);
            return new ResponseEntity<>("Your new password:" + user.getPassword(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("forgotPassword/{username}")
    public ResponseEntity<String> forgotPassword(@PathVariable String username) {
        try {
            User user = userService.findUserByUsername(username);
            return new ResponseEntity<>("Your password:" + user.getPassword(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public boolean isAdmin() {
        if (!loggedUser.equals(null)) {
            String user_role = loggedUser.getRole().getRole_name().toString().toUpperCase();
            return user_role.equals(RoleName.ADMIN.toString().toUpperCase());
        } else {
            return false;
        }
    }
}
