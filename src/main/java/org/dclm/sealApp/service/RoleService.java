package org.dclm.sealApp.service;

import org.dclm.sealApp.model.Permission;
import org.dclm.sealApp.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role add(Role role);
    Optional<Role> findByName(String name);
    List<Role> findAll();
    Optional<Role> findById(Long id);
    List<Permission> getAllRolePermissions(Long id);
}
