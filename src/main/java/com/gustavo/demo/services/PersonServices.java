package com.gustavo.demo.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.demo.data.vo.v1.PersonVO;
import com.gustavo.demo.exceptions.ResourceNotFoundException;
import com.gustavo.demo.mapper.DozerMapper;
import com.gustavo.demo.model.Person;
import com.gustavo.demo.repositories.PersonRepository;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;
    
    public List<PersonVO> findAll(){
        logger.info("Find all people!");

        List<Person> listPerson = repository.findAll();        

        return DozerMapper.parseListObjects(listPerson, PersonVO.class);
    }

    public PersonVO findByID(Long id){
        logger.info("Find one person!");

        Person person = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No records found for this ID!")
        );

        return DozerMapper.parseObject(person, PersonVO.class); 
    }

    public PersonVO create(PersonVO person){
        logger.info("Creating one person");

        return DozerMapper.parseObject(
            repository.save(
                DozerMapper.parseObject(person, Person.class)
            ),
            PersonVO.class
        );
    }

    public PersonVO update(PersonVO personVO){
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
            PersonVO.class
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
