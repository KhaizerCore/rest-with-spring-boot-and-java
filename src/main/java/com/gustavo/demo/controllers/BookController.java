package com.gustavo.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.demo.data.vo.v1.BookVO;
import com.gustavo.demo.services.BookServices;
import com.gustavo.demo.util.CustomMediaType;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController()
@RequestMapping("/api/book/v1")
public class BookController {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BookServices service;

    @GetMapping(
        value = "/{id}"
    )
    public BookVO findBookByID(
        @PathVariable(value = "id") Integer id
    ){
        return service.findByID(id);
    } 

    @GetMapping()
    public List<BookVO> findAll(){
        return service.findAll();
    }

    @PostMapping(
        consumes = {
            CustomMediaType.APPLICATION_JSON, 
            CustomMediaType.APPLICATION_XML, 
            CustomMediaType.APPLICATION_YML
        },
        produces = {
            CustomMediaType.APPLICATION_JSON, 
            CustomMediaType.APPLICATION_XML, 
            CustomMediaType.APPLICATION_YML
        }
    )
    public BookVO create(@RequestBody BookVO bookVO){
        return service.create(bookVO);
    }

    @PutMapping(
        consumes = {
            CustomMediaType.APPLICATION_JSON, 
            CustomMediaType.APPLICATION_XML, 
            CustomMediaType.APPLICATION_YML
        },
        produces = {
            CustomMediaType.APPLICATION_JSON, 
            CustomMediaType.APPLICATION_XML, 
            CustomMediaType.APPLICATION_YML
        }
    )
    public BookVO update(@RequestBody BookVO bookVO){
        return service.update(bookVO);
    }

    @DeleteMapping()
    public void delete(Integer id){
        service.delete(id);
    }

    
}
