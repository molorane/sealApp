package org.dclm.sealApp.controller;

import org.dclm.sealApp.model.Continent;
import org.dclm.sealApp.model.Country;
import org.dclm.sealApp.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/continents")
public class ContinentController {

    @Autowired
    private ContinentService continentService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllContinents() {
        List<Continent> continents  =  continentService.findAll();
        if(continents == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(continents, HttpStatus.OK);
    }

    @GetMapping("/continents/{continent_id}/countries")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllCountriesInContinent(@PathVariable("continent_id") Long continent_id){
        List<Country> countriesInContinent  =  continentService.getAllCountriesInContinent(continent_id);
        if(countriesInContinent == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(countriesInContinent, HttpStatus.OK);
    }
}
