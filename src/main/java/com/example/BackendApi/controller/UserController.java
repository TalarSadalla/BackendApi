package com.example.BackendApi.controller;

import com.example.BackendApi.entity.User;
import com.example.BackendApi.repository.RoleName;
import com.example.BackendApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    @Autowired
    private AuthController authController;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> list() {
        if (authController.isAdmin()) {
            return userService.findAllUsers();
        } else {
            return new ArrayList<>();
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        if (authController.isAdmin()) {
            try {
                User user = userService.getUser(id.longValue());
                return new ResponseEntity<User>(user, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public void add(@RequestBody User user) {
        if (authController.isAdmin() || isUserRole(authController)) {
            userService.saveUser(user);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
        if (authController.isAdmin()) {
            try {
                User existUser = userService.getUser(id.longValue());
                userService.saveUser(existUser);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("You don't have access", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (authController.isAdmin()) {
            try {
                userService.deleteUser(id.longValue());
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>("No such user with this id", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("You don't have access", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Wrong data", HttpStatus.NOT_FOUND);
    }

    private boolean isUserRole(AuthController authController) {
        String user_role = authController.loggedUser.getRole().getRole_name().toString().toUpperCase();
        String adminRoleName = RoleName.USER.toString().toUpperCase();
        return user_role.equals(adminRoleName);
    }
}
