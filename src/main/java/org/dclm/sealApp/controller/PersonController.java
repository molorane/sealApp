package org.dclm.sealApp.controller;

import org.dclm.sealApp.model.Person;
import org.dclm.sealApp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{idno}")
    public ResponseEntity<?> getPersonById(@PathVariable("idno") String idno){
        Person person  =  personService.findByIdno(idno);
        if(person == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

}
