package com.example.BackendApi.repository;

import com.example.BackendApi.entity.Role;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public enum Permission {

    CREATE_USER(1, "Create user"),

    UPDATE_USER(2, "Update user"),

    DELETE_USER(3, "Delete user"),

    LIST_USERS(4, "List of users");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int permission_id;

    private String permission_name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    Permission(int permission_id, String permissionType) {
        this.permission_id = permission_id;
        this.permission_name = permissionType;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }
}
