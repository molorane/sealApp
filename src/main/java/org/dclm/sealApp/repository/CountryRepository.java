package org.dclm.sealApp.repository;

import org.dclm.sealApp.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
