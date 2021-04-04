package org.dclm.sealApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Country extends AbstractEntity {

    @NotEmpty(message = "country code required.")
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 4, message = "State abv must have min 2 and max 4 characters.")
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="continent_id", nullable=false)
    private Continent continent;

    @OneToMany(mappedBy="country", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<State> states = new ArrayList<>();

}