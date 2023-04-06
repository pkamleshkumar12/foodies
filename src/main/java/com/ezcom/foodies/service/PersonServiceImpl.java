package com.ezcom.foodies.service;

import com.ezcom.foodies.collection.Person;
import com.ezcom.foodies.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public String save(Person person) {
        return personRepository.save(person).getPersonId();
    }
}
