package com.example.bookauthor_app.repository;

import com.example.bookauthor_app.entity.Author;
import com.example.bookauthor_app.util.STATUS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findAuthorByAuthorName(String authorName);
}
