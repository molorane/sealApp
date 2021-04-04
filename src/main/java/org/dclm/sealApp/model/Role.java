package org.dclm.sealApp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "app_role")
public class Role extends AbstractEntity {

    @Column
    private String desc;

    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(
                    name = "role", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "permission", referencedColumnName = "id"))
    private Collection<Permission> permissions;

}
