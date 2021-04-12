package org.dclm.sealApp.controller;

import org.dclm.sealApp.model.Permission;
import org.dclm.sealApp.model.Role;
import org.dclm.sealApp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAllRoles(){
        List<Role> roles  =  roleService.findAll();
        if(roles == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{roleId}/permissions")
    public ResponseEntity<?> getAllRolePermissions(@PathVariable("roleId") Long roleId){
        List<Permission> permissions  =  roleService.getAllRolePermissions(roleId);
        if(permissions == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }
}
