package org.dclm.sealApp.repository;

import org.dclm.sealApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByEmail(String email);
	Optional<Account> findByUsername(String username);
	Optional<Account> findById(Long id);

}
