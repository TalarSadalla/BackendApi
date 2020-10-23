package com.example.BackendApi.entity;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username;
    @NotNull
    private String password;

    private List<Role> role;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id"))
    public List<Role> getRoleList() {
        return this.role;
    }
}
