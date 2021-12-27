package com.example.bookauthor_app.service.author;

import com.example.bookauthor_app.dto.AuthorDTO;
import com.example.bookauthor_app.projections.AuthorBookCount;
import com.example.bookauthor_app.exception.NotFoundException;

import java.util.List;

public interface AuthorInterface {
    void addAuthor(AuthorDTO authorDTO);
    List<AuthorDTO> getAllAuthors();
    void updateAuthor(AuthorDTO authorDTO, Long id) throws NotFoundException;
    void deleteAuthor(Long id) throws NotFoundException;
    AuthorDTO getAuthorById(Long id);
    List<AuthorBookCount> filterAuthorByBookCount(int counter);
}
