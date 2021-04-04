package org.dclm.sealApp.service.impl;

import lombok.AllArgsConstructor;
import org.dclm.sealApp.model.Continent;
import org.dclm.sealApp.model.Country;
import org.dclm.sealApp.repository.ContinentRepository;
import org.dclm.sealApp.service.ContinentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContinentServiceImpl implements ContinentService {

    private final ContinentRepository continentRepository;

    @Override
    public Continent save(Continent continent) {
        return continentRepository.save(continent);
    }

    @Override
    public Optional<Continent> findByName(String name) {
        return continentRepository.findByName(name);
    }

    @Override
    public List<Continent> findAll() {
        return continentRepository.findAll();
    }

    @Override
    public Optional<Continent> findById(Long id) {
        return continentRepository.findById(id);
    }

    @Override
    public List<Country> getAllCountriesInContinent(Long continent_id) {
        return continentRepository.getAllCountriesInContinent(continent_id);
    }
}
