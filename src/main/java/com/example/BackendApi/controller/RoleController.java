package com.example.BackendApi.controller;

import com.example.BackendApi.entity.Role;
import com.example.BackendApi.repository.RoleName;
import com.example.BackendApi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RoleController {

    private AuthController authController;

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public List<Role> list() {
        return roleService.findAllRoles();
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> get(@PathVariable Integer id) {
        if (isAdmin()) {
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

    private boolean isAdmin() {
        return authController.loggedUser.getRoles().contains(RoleName.ADMIN);
    }

    @PostMapping("/roles")
    public void add(@RequestBody Role role) {
        if (isAdmin()) {
            roleService.saveRole(role);
        }
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<?> update(@RequestBody Role role, @PathVariable Integer id) {
        if (isAdmin()) {
            try {
                Role existRole = roleService.getRole(id.longValue());
                roleService.saveRole(existRole);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/roles/{id}")
    public void delete(@PathVariable Integer id) {
        if (isAdmin()) {
            roleService.deleteRole(id.longValue());
        }
    }

}