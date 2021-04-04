package org.dclm.sealApp.repository;

import org.dclm.sealApp.model.Continent;
import org.dclm.sealApp.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContinentRepository extends JpaRepository<Continent, Long> {

    Optional<Continent> findByName(String name);


    @Query("SELECT ct FROM Country ct " +
            "INNER JOIN FETCH ct.continent co " +
            "WHERE co.id = :continentId")
    List<Country> getAllCountriesInContinent(@Param("continentId") Long continentId);
}