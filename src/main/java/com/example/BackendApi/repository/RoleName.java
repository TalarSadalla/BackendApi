package com.example.BackendApi.repository;

import javax.persistence.*;

@Entity
@Table(name = "rolename")
public enum RoleName {

    ADMIN("Admin"),

    USER("User");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rolename_id;

    RoleName(String user) {
    }
}
