package com.example.BackendApi.entity;

import com.example.BackendApi.logic.Permission;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String rolename;

    private List<Permission> permissionList;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id"))
    public List<Permission> getRoleList() {
        return this.permissionList;
    }
}
