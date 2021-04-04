package org.dclm.sealApp.service;

import org.dclm.sealApp.model.Permission;
import org.dclm.sealApp.model.Role;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    Permission add(Permission permission);
    Optional<Permission> findByName(String name);
    List<Permission> findAll();
    Optional<Permission> findById(Long id);
    List<Role> getAllPermissionRoles(Long id);
}
