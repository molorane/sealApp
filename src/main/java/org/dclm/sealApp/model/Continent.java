package org.dclm.sealApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;


@Data
@Entity
@Table(name = "continent")
public class Continent extends AbstractEntity{

    @OneToMany(mappedBy="continent", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Country> countries = new HashSet<>();
}
