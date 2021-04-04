package org.dclm.sealApp.repository;

import org.dclm.sealApp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {

    Optional<Person> findByAccountId(Long idno);
}
