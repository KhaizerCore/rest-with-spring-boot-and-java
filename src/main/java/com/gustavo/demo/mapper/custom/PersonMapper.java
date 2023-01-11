package com.gustavo.demo.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.gustavo.demo.data.vo.v2.PersonVOv2;
import com.gustavo.demo.model.Person;

@Service
public class PersonMapper {
    
    public static PersonVOv2 convertEntityToVOv2(Person person) {
        PersonVOv2 vov2 = new PersonVOv2();
        vov2.setId(person.getId());
        vov2.setFirstName(person.getFirstName());
        vov2.setLastName(person.getLastName());
        vov2.setAddress(person.getAddress());
        vov2.setGender(person.getGender());
        vov2.setBirthday(new Date());
        return vov2;
    }

    public static Person convertVOv2ToEntity(PersonVOv2 personVOv2) {
        Person entity = new Person();
        entity.setId(personVOv2.getId());
        entity.setFirstName(personVOv2.getFirstName());
        entity.setLastName(personVOv2.getLastName());
        entity.setAddress(personVOv2.getAddress());
        entity.setGender(personVOv2.getGender());
        return entity;
    }
}
