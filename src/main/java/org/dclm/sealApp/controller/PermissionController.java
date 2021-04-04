package org.dclm.sealApp.controller;

import org.dclm.sealApp.model.Permission;
import org.dclm.sealApp.model.Role;
import org.dclm.sealApp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/permissions")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<?> getAllPermissions(){
        List<Permission> permissions  =  permissionService.findAll();
        if(permissions == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @GetMapping("/{permissionId}/roles")
    public ResponseEntity<?> getAllRolePermissions(@PathVariable("permissionId") Long permissionId){
        List<Role> roles  =  permissionService.getAllPermissionRoles(permissionId);
        if(roles == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
