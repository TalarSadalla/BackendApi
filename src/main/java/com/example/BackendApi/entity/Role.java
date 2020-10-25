package com.example.BackendApi.entity;

import com.example.BackendApi.repository.Permission;
import com.example.BackendApi.repository.RoleName;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_id;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private RoleName role_name;

    public Long getRole_id() {
        return role_id;
    }

    public RoleName getRole_name() {
        return role_name;
    }

    public void setRole_name(RoleName role_name) {
        this.role_name = role_name;
    }

    @ManyToMany(mappedBy = "roles")
    private List<User> userList = new ArrayList<>();


    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "permission_list",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;

    public List<User> getList() {
        return this.userList;
    }

    public List<Permission> getPermissions() {
        return this.permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(role_id, role.role_id) &&
                Objects.equals(role_name, role.role_name) &&
                Objects.equals(permissions, role.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role_id, role_name, permissions);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + role_id +
                ", rolename='" + role_name + '\'' +
                ", permissionList=" + permissions +
                '}';
    }
}
