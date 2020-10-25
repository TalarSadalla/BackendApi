package com.example.BackendApi.entity;

import com.example.BackendApi.repository.RoleName;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @OneToOne(mappedBy = "role", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private User user;

    public User getUser() {
        return this.user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(role_id, role.role_id) &&
                Objects.equals(role_name, role.role_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role_id, role_name);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + role_id +
                ", rolename='" + role_name + '\'' +
                '}';
    }
}
