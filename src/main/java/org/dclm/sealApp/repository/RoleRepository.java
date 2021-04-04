package org.dclm.sealApp.repository;

import org.dclm.sealApp.model.Permission;
import org.dclm.sealApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    @Query("SELECT r.permissions FROM Role r " +
            "WHERE r.id = :roleId")
    List<Permission> getAllRolePermissions(@Param("roleId") Long id);
}
