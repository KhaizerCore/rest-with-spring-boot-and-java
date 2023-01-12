package com.gustavo.demo.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.demo.data.vo.v1.PersonVO;
import com.gustavo.demo.services.PersonServices;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    // private PersonServices service = new PersonServices();
    @Autowired
    private PersonServices service;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PersonVO> findAll() throws Exception  {
        return service.findAll();
    }

    @GetMapping(
        value = "/{id}", 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonVO findById( @PathVariable(value = "id") Long id ) throws Exception  {
        return service.findByID(id);
    }    

    @PostMapping(
        value = "",
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonVO create(@RequestBody PersonVO person) throws Exception  {
        return service.create(person);
    }

    @PutMapping(
        value = "",
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonVO update(@RequestBody PersonVO person) throws Exception  {
        return service.update(person);
    }

    @DeleteMapping(
        value = "/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception  {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
