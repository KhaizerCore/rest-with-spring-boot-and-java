package com.gustavo.demo.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.gustavo.demo.controllers.PersonController;
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
    
    public List<PersonVO> findAll() throws Exception{
        logger.info("Find all people!");

        List<Person> listPerson = repository.findAll();        

        var personsVO = DozerMapper.parseListObjects(listPerson, PersonVO.class);

        for (PersonVO personVO : personsVO){
            personVO.add(
                linkTo(
                    methodOn(PersonController.class).findById(personVO.getKey())
                ).withSelfRel()
            );
        }

        return personsVO;
    }

    public PersonVO findByID(Long id) throws Exception{
        logger.info("Find one person!");

        Person entity = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No records found for this ID!")
        );

        PersonVO personVO = DozerMapper.parseObject(entity, PersonVO.class); 

        personVO.add(
            linkTo(
                methodOn(PersonController.class).findById(id)
            ).withSelfRel()
        );

        return personVO;
    }

    public PersonVO create(PersonVO person) throws Exception{
        logger.info("Creating one person");

        PersonVO personVO = DozerMapper.parseObject(
            repository.save(
                DozerMapper.parseObject(person, Person.class)
            ),
            PersonVO.class
        );

        personVO.add(
            linkTo(
                methodOn(PersonController.class).findById(personVO.getKey())
            ).withSelfRel()
        );

        return personVO;
    }

    public PersonVO update(PersonVO personVO) throws Exception{
        logger.info("Updating one person");

        var person = DozerMapper.parseObject(personVO, Person.class);

        var entity = repository.findById(person.getId()).orElseThrow(
            () -> new ResourceNotFoundException("No records found for this ID!")
        );

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO personVORet = DozerMapper.parseObject(
            repository.save(entity),
            PersonVO.class
        );

        personVORet.add(
            linkTo(
                methodOn(PersonController.class).findById(personVORet.getKey())
            ).withSelfRel()
        );

        return personVORet;
    }

    public void delete(Long id){
        logger.info("Deleting one person!");

        var entity = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No records found for this ID!")
        );

        repository.delete(entity);
    }
}
