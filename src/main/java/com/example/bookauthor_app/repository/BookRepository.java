package com.example.bookauthor_app.repository;

import com.example.bookauthor_app.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByBookName(String bookName);
}
