package org.dclm.sealApp.service;

import org.dclm.sealApp.model.Continent;
import org.dclm.sealApp.model.Country;

import java.util.List;
import java.util.Optional;

public interface ContinentService {
    Continent save(Continent continent);
    Optional<Continent> findByName(String name);
    List<Continent> findAll();
    Optional<Continent> findById(Long id);
    List<Country> getAllCountriesInContinent(Long id);
}
