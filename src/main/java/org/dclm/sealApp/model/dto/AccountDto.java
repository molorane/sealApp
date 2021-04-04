package org.dclm.sealApp.model.dto;

import lombok.Data;
import org.dclm.sealApp.utils.FieldMatch;

import javax.validation.constraints.Email;

@Data
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class AccountDto {
    private String username;
    @Email(message = "Invalid email format")
    private String email;
    private String password;
    private String confirmPassword;
}
