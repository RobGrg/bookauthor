package com.example.bookauthor_app.repository;

import com.example.bookauthor_app.entity.Author;
import com.example.bookauthor_app.util.STATUS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findAuthorByAuthorName(String authorName);
}
