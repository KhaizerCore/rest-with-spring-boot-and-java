package com.gustavo.demo.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.demo.data.vo.v2.PersonVOv2;
import com.gustavo.demo.mapper.custom.PersonMapper;

import com.gustavo.demo.repositories.PersonRepository;

@Service
public class PersonServicesV2 {

    private Logger logger = Logger.getLogger(PersonServicesV1.class.getName());

    @Autowired
    private PersonRepository repository;
    
    public PersonVOv2 create(PersonVOv2 personVOv2){
        logger.info("Creating one person with v2!");

        return PersonMapper.convertEntityToVOv2(
            repository.save(PersonMapper.convertVOv2ToEntity(personVOv2))
        );
    }
}
