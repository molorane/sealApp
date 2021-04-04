package org.dclm.sealApp.service;


import org.dclm.sealApp.model.Person;

public interface PersonService {

    Person findByIdno(String idno);
    Person addNewPerson(Person person);
}
