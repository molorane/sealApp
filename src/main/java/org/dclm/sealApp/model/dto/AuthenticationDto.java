package org.dclm.sealApp.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.dclm.sealApp.model.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
public class AuthenticationDto {
    private Long id;
    private String username;
    private int status;
    private boolean isLocked;
    private boolean isActive;
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;
    private String profile;
    private Set<Role> roles;
    private String authToken;
}
