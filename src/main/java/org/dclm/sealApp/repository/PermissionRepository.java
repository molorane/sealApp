package org.dclm.sealApp.repository;

import org.dclm.sealApp.model.Permission;
import org.dclm.sealApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByName(String name);

    @Query("SELECT p.roles FROM Permission p " +
            "WHERE p.id = :permissionId")
    List<Role> getAllPermissionRoles(@Param("permissionId") Long id);

}
