package com.gustavo.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.demo.data.vo.v1.BookVO;
import com.gustavo.demo.services.BookServices;
import com.gustavo.demo.util.CustomMediaType;

@RestController()
@RequestMapping("/api/book/v1")
public class BookController {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BookServices service;

    @GetMapping(
        value = "/{id}", 
        consumes = {
            CustomMediaType.APPLICATION_JSON, 
            CustomMediaType.APPLICATION_XML, 
            CustomMediaType.APPLICATION_YML
        }
    )
    public BookVO findBookByID(
        @PathVariable(value = "id") Integer id
    ){
        return service.findByID(id);
    } 

    public List<BookVO> findAll(){
        return service.findAll();
    }

    public BookVO create(BookVO bookVO){
        return service.create(bookVO);
    }

    public BookVO update(BookVO bookVO){
        return service.update(bookVO);
    }

    public void delete(Integer id){
        service.delete(id);
    } 

    
}
