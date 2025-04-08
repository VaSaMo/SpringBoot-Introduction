package com.example.introspring.service.impl;

import com.example.introspring.entity.Role;
import com.example.introspring.repository.RoleRepository;
import com.example.introspring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

}
