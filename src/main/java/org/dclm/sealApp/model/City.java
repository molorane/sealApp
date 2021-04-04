package org.dclm.sealApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "city")
public class City extends AbstractEntity {

    @NotEmpty(message = "city abv required.")
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 4, message = "City abv must have min 2 and max 4 characters.")
    private String abv;

    @ManyToOne
    @JoinColumn(name="state_id", nullable=false)
    private State state;
}