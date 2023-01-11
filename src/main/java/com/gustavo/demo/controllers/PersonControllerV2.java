package com.gustavo.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.demo.data.vo.v2.PersonVOv2;
import com.gustavo.demo.services.PersonServicesV2;


@RestController
@RequestMapping("v2/person")
public class PersonControllerV2 {

    // private PersonServices service = new PersonServices();
    @Autowired
    private PersonServicesV2 service;

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonVOv2 create(@RequestBody PersonVOv2 personVOv2){
        return service.create(personVOv2);
    }

}
