package org.dclm.sealApp.model.dto;

import lombok.Data;
import org.dclm.sealApp.model.enums.Gender;
import org.dclm.sealApp.model.enums.MaritalStatus;
import org.dclm.sealApp.model.enums.Race;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class PersonDto {
    private String firstName;
    private String lastName;
    private String otherName;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private Race race;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
}
