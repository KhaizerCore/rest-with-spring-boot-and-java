package com.gustavo.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.demo.model.Book;
import com.gustavo.demo.data.vo.v1.BookVO;
import com.gustavo.demo.exceptions.RequiredObjectIsNullException;
import com.gustavo.demo.exceptions.ResourceNotFoundException;
import com.gustavo.demo.mapper.DozerMapper;
import com.gustavo.demo.repositories.BookRepository;

@Service
public class BookServices {
    
    private static final long serialVersionUID = 1L; 

    @Autowired
    private BookRepository repository;

    public BookVO findByID(Integer id){

        Book bookEntity = repository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Invalid Book Id");
        });

        return DozerMapper.parseObject(
            bookEntity,
            BookVO.class
        );
    }

    public List<BookVO> findAll(){
        return DozerMapper.parseListObjects(
            repository.findAll(),
            BookVO.class
        );
    }

    public BookVO create(BookVO bookVO){
        return DozerMapper.parseObject(
            repository.save(
                DozerMapper.parseObject(bookVO, Book.class)
            ),
            BookVO.class
        );
    }

    public BookVO update(BookVO bookVO){
        if (bookVO == null) throw new RequiredObjectIsNullException();
        if (bookVO.getKey() == null) throw new RequiredObjectIsNullException();

        Book bookEntity = repository.findById(bookVO.getKey()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Invalid Book Id");
        });

        return DozerMapper.parseObject(bookEntity, BookVO.class);
    }

    public void delete(Integer id){
        
        Book bookEntity = repository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Invalid Book Id");
        });

        repository.delete(bookEntity);
    } 
    
}
