package com.example.BackendApi.repository;

import com.example.BackendApi.entity.Role;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roleList;

    public List<Role> roleList() {
        return this.roleList;
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
