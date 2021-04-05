package org.dclm.sealApp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "name required.")
    @Column(nullable = false, unique = true)
    private String name;
}
