package com.gustavo.demo.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.gustavo.demo.util.CustomMediaType;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    // private PersonServices service = new PersonServices();
    @Autowired
    private PersonServices service;

    @GetMapping(
        produces = {CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_XML, CustomMediaType.APPLICATION_YML}
    )
    public List<PersonVO> findAll() throws Exception  {
        return service.findAll();
    }

    @GetMapping(
        value = "/{id}", 
        produces = {CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_XML, CustomMediaType.APPLICATION_YML}
    )
    public PersonVO findById( @PathVariable(value = "id") Long id ) throws Exception  {
        return service.findByID(id);
    }    

    @PostMapping(
        value = "",
        consumes = {CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_XML, CustomMediaType.APPLICATION_YML}, 
        produces = {CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_XML, CustomMediaType.APPLICATION_YML}
    )
    public PersonVO create(@RequestBody PersonVO person) throws Exception  {
        return service.create(person);
    }

    @PutMapping(
        value = "",
        consumes = {CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_XML, CustomMediaType.APPLICATION_YML}, 
        produces = {CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_XML, CustomMediaType.APPLICATION_YML}
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
