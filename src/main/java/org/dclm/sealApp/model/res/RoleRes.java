package org.dclm.sealApp.model.res;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@JsonDeserialize
@AllArgsConstructor
@NoArgsConstructor
public class RoleRes extends RepresentationModel<RoleRes> {
    private Long id;
    private String name;
    private String desc;
}
