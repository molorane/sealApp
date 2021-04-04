package org.dclm.sealApp.service.impl;

import org.dclm.sealApp.model.Permission;
import org.dclm.sealApp.model.Role;
import org.dclm.sealApp.repository.PermissionRepository;
import org.dclm.sealApp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    private PermissionRepository permisssionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permisssionRepository) {
        this.permisssionRepository = permisssionRepository;
    }


    @Override
    public Permission add(Permission permission) {
        return permisssionRepository.save(permission);
    }

    @Override
    public Optional<Permission> findByName(String name) {
        return permisssionRepository.findByName(name);
    }

    @Override
    public List<Permission> findAll() {
        return permisssionRepository.findAll();
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return permisssionRepository.findById(id);
    }

    @Override
    public List<Role> getAllPermissionRoles(Long id) {
        return permisssionRepository.getAllPermissionRoles(id);
    }
}
