package com.example.bookauthor_app.repository;

import com.example.bookauthor_app.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
