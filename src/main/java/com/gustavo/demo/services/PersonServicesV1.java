package com.gustavo.demo.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.demo.data.vo.v1.PersonVOv1;
import com.gustavo.demo.exceptions.ResourceNotFoundException;
import com.gustavo.demo.mapper.DozerMapper;
import com.gustavo.demo.model.Person;
import com.gustavo.demo.repositories.PersonRepository;

@Service
public class PersonServicesV1 {

    private Logger logger = Logger.getLogger(PersonServicesV1.class.getName());

    @Autowired
    private PersonRepository repository;
    
    public List<PersonVOv1> findAll(){
        logger.info("Find all people!");

        List<Person> listPerson = repository.findAll();        

        return DozerMapper.parseListObjects(listPerson, PersonVOv1.class);
    }

    public PersonVOv1 findByID(Long id){
        logger.info("Find one person!");

        Person person = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No records found for this ID!")
        );

        return DozerMapper.parseObject(person, PersonVOv1.class); 
    }

    public PersonVOv1 create(PersonVOv1 person){
        logger.info("Creating one person with v1!");

        return DozerMapper.parseObject(
            repository.save(
                DozerMapper.parseObject(person, Person.class)
            ),
            PersonVOv1.class
        );
    }

    public PersonVOv1 update(PersonVOv1 personVO){
        logger.info("Updating one person");

        var person = DozerMapper.parseObject(personVO, Person.class);

        var entity = repository.findById(person.getId()).orElseThrow(
            () -> new ResourceNotFoundException("No records found for this ID!")
        );

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerMapper.parseObject(
            repository.save(entity),
            PersonVOv1.class
        );
    }

    public void delete(Long id){
        logger.info("Deleting one person!");

        var entity = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No records found for this ID!")
        );

        repository.delete(entity);
    }
}
