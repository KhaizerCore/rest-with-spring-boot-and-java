package com.gustavo.demo.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Answers.valueOf;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gustavo.demo.data.vo.v1.PersonVO;
import com.gustavo.demo.exceptions.RequiredObjectIsNullException;
import com.gustavo.demo.mapper.DozerMapper;
import com.gustavo.demo.model.Person;
import com.gustavo.demo.repositories.PersonRepository;
import com.gustavo.demo.services.PersonServices;
import com.gustavo.demo.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {
    
    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;


    @BeforeEach
    void setUpMocks() throws Exception{
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() throws Exception {
        
        Integer personID = 1;
        
        Person entity = input.mockEntity(personID);
        PersonVO personVO = input.mockVO(personID);
        
        Person persisted = entity;
        persisted.setId(personID.longValue());

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(personVO);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        //System.out.println(result.toString());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());

    }

    @Test
    void testCreateWithNullPerson() throws Exception {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, ()->{
            service.create(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void testDelete() {

    }

    @Test
    void testFindAll() throws Exception {
        List<Person> personList = input.mockEntityList();

        when(repository.findAll()).thenReturn(personList);

        List<PersonVO> people = service.findAll();

        assertTrue(people.size() == 14);

        for (Integer idx = 0; idx < people.size(); idx++){
            PersonVO person = people.get(idx);

            assertNotNull(person);
            assertNotNull(person.getKey());
            assertNotNull(person.getLinks());
            System.out.println("!!!!!!!! " + person.toString());
            assertTrue(person.toString().contains("links: [</api/person/v1/"+idx.toString()+">;rel=\"self\"]"));
            
            assertEquals(
                "Address Test" + idx.toString(),
                person.getAddress()
            );

            assertEquals(
                "First Name Test" + idx.toString(),
                person.getFirstName()
            );

            assertEquals(
                "Last Name Test" + idx.toString(),
                person.getLastName()
            );

            assertEquals(
                idx.intValue() % 2 == 0 ? "Male" : "Female",
                person.getGender()
            );

            assertEquals(
                "Address Test" + idx.toString(),
                person.getAddress()
            );

            assertEquals(
                idx.longValue(),
                person.getKey()
            );
        }

        /*
        14 people
        person.setAddress("Address Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
         */
    }

    @Test
    void testFindByID() throws Exception {
        Integer personID = 1;
        Person entity = input.mockEntity(personID);

        when(repository.findById(personID.longValue())).thenReturn(Optional.of(entity));

        var result = service.findByID(personID.longValue());
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testFindByIDWithNullID() throws Exception {
        
        assertTrue(
            assertThrows(
                RequiredObjectIsNullException.class,
                () -> { service.findByID(null); }
            ).getMessage().contains("It is not allowed to persist a null object!")
        );

    }

    @Test
    void testUpdate() throws Exception {
        Integer personID = 1;
        
        Person entity = input.mockEntity(personID);

        Person persisted = entity;

        PersonVO vo = input.mockVO(personID);

        when(repository.findById(personID.longValue())).thenReturn(Optional.of(entity));

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdateWithNullPerson() throws Exception {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, ()->{
            service.update(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
