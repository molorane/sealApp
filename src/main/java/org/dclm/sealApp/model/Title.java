package org.dclm.sealApp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Title extends AbstractEntity {

    @NotEmpty(message = "title abv required.")
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 4, message = "Tile abv must have min 2 and max 4 characters.")
    private String abv;
}
