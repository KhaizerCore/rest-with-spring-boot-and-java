package com.test.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.exceptions.ResourceNotFoundException;
import com.test.demo.model.Person;
import com.test.demo.repositories.PersonRepository;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;
    
    public List<Person> findAll(){
        logger.info("Find all people!");

        return repository.findAll();
    }

    public Person findByID(Long id){
        logger.info("Find one person!");

        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No records found for this ID!")
        );
    }

    public Person create(Person person){
        logger.info("Creating one person");

        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating one person");

        var entity = repository.findById(person.getId()).orElseThrow(
            () -> new ResourceNotFoundException("No records found for this ID!")
        );

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting one person!");

        var entity = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No records found for this ID!")
        );

        repository.delete(entity);
    }
}
