package com.test.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.test.demo.model.Person;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());
    
    public Person findByID(String id){
        logger.info("Find one person!");

        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("Gustavo");
        person.setLastName("Ferreira");
        person.setAddress("Sao Paulo, Sao Paulo");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll(){
        logger.info("Find all people!");

        List<Person> persons = new ArrayList<Person>();

        for (int i = 0; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Last Name " + i);
        person.setAddress("Some address " + i);
        person.setGender( i % 2 == 0 ? "Male" : "Female");

        return person;
    }

    public Person create(Person person){
        logger.info("Creating one person");
        return person;
    }

    public Person update(Person person){
        logger.info("Updating one person");
        return person;
    }

    public void delete(String id){
        logger.info("Deleting one person!");
    }
}
