package com.example.bookauthor_app.repository;

import com.example.bookauthor_app.entity.Author;
import com.example.bookauthor_app.projections.AuthorBookCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findAuthorByAuthorName(String authorName);

    @Query(nativeQuery = true)
    List<AuthorBookCount> findAuthorBookCount(@Param("counter") int counter);
}
