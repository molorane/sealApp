package org.dclm.sealApp.service;

import org.dclm.sealApp.model.Account;
import org.dclm.sealApp.model.dto.RegisterDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface AccountService extends UserDetailsService {
    Account register(RegisterDto user);
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    List<Account> findAll();
    Optional<Account> findById(Long id);
}
