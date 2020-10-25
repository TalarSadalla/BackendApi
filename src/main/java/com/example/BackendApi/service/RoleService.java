package com.example.BackendApi.service;

import com.example.BackendApi.entity.Role;
import com.example.BackendApi.repository.api.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public Role getRole(Long id) {
        return roleRepository.findById(id).get();
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
