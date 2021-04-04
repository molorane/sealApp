package org.dclm.sealApp.repository;

import org.dclm.sealApp.model.City;
import org.dclm.sealApp.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {

    @Query("SELECT st FROM State st " +
            "INNER JOIN FETCH st.country ct " +
            "WHERE ct.id = :countryId")
    List<City> getAllStatesInCountry(@Param("countryId") Long countryId);

    @Query("SELECT st FROM State st " +
            "INNER JOIN FETCH st.country ct " +
            "WHERE ct.name = :countryName")
    List<City> getAllStatesInCountry(@Param("countryName") String countryName);

}
