package org.dclm.sealApp.repository;

import org.dclm.sealApp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT ct FROM City ct " +
            "INNER JOIN FETCH ct.state st " +
            "WHERE st.name = :stateName")
    List<City> getAllCitiesInState(@Param("stateName") String stateName);

    @Query("SELECT ct FROM City ct " +
            "INNER JOIN FETCH ct.state st " +
            "WHERE st.id = :stateId")
    List<City> getAllCitiesInState(@Param("stateId") Long stateId);

}
