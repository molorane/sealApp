package org.dclm.sealApp.service.impl;

import org.dclm.sealApp.exceptions.DuplicateEntryException;
import org.dclm.sealApp.model.Person;
import org.dclm.sealApp.repository.PersonRepository;
import org.dclm.sealApp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person findByIdno(String idno) {
        return personRepository.findById(idno).get();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Person addNewPerson(Person person) {
        if(personRepository.findByAccountId(person.getAccount().getId()).isPresent()){
            throw new DuplicateEntryException("ID number "+person.getAccount().getId()+" taken already!!");
        }
        return personRepository.save(person);
    }
}
