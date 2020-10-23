package com.example.BackendApi.logic;

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
    private int id;

    private String text;

    Permission(int id, String permissionType) {
        this.id = id;
        this.text = permissionType;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
