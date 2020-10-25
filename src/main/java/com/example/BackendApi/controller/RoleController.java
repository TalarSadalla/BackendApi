package com.example.BackendApi.controller;

import com.example.BackendApi.entity.Role;
import com.example.BackendApi.repository.Permission;
import com.example.BackendApi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RoleController {

    @Autowired
    private AuthController authController;


    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public List<Role> list() {
        return roleService.findAllRoles();
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> get(@PathVariable Integer id) {
        if (authController.isAdmin()) {
            try {
                Role role = roleService.getRole(id.longValue());
                return new ResponseEntity<Role>(role, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/roles")
    public void add(@RequestBody Role role) {
        Long role_id = authController.loggedUser.getRole().getRole_id();
        int permission_id = Permission.CREATE_USER.getPermission_id();

        if (authController.isAdmin() && permission_id == role_id.intValue()) {
            roleService.saveRole(role);
        }
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<?> update(@RequestBody Role role, @PathVariable Integer id) {
        if (authController.isAdmin()) {
            try {
                Role existRole = roleService.getRole(id.longValue());
                roleService.saveRole(existRole);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("You don't have access", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (authController.isAdmin()) {
            try {
                roleService.deleteRole(id.longValue());
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>("No such role id to delete", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("You don't have access", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Wrong data", HttpStatus.NOT_FOUND);
    }

}