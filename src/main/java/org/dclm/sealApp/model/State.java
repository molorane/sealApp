package org.dclm.sealApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "state")
@Data
public class State extends AbstractEntity{

    @NotEmpty(message = "state code required.")
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 4, message = "State abv must have min 2 and max 4 characters.")
    private String code;

    @ManyToOne
    @JoinColumn(name="country_id", nullable=false)
    private Country country;

    @OneToMany(mappedBy="state", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<City> cities = new ArrayList<>();

}