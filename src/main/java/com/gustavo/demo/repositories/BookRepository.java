package com.gustavo.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavo.demo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {}
