package org.dclm.sealApp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Permission extends AbstractEntity{

    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;
}
