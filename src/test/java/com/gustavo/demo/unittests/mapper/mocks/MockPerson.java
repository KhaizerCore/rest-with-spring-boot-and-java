package com.gustavo.demo.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import com.gustavo.demo.data.vo.v1.PersonVOv1;
import com.gustavo.demo.model.Person;


public class MockPerson {


    public Person mockEntity() {
        return mockEntity(0);
    }
    
    public PersonVOv1 mockVO() {
        return mockVO(0);
    }
    
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonVOv1> mockVOList() {
        List<PersonVOv1> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }
    
    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setAddress("Addres Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

    public PersonVOv1 mockVO(Integer number) {
        PersonVOv1 person = new PersonVOv1();
        person.setAddress("Addres Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

}
