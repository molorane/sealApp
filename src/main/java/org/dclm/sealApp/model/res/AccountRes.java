package org.dclm.sealApp.model.res;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonDeserialize
@AllArgsConstructor
@NoArgsConstructor
public class AccountRes extends RepresentationModel<AccountRes> {

    private Long id;
    private String username;
    private String email;
    private int status;
    private boolean isLocked;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDate expiryDate;
    private String profile;

}
