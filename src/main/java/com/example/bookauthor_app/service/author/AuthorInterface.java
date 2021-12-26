package com.example.bookauthor_app.service.author;

import com.example.bookauthor_app.dto.AuthorDTO;
import com.example.bookauthor_app.entity.Author;

import java.util.List;

public interface AuthorInterface {
    void addAuthor(AuthorDTO authorDTO);
    List<AuthorDTO> getAllAuthors();
    void updateAuthor(AuthorDTO authorDTO);
    void removeAuthor(AuthorDTO authorDTO);
    AuthorDTO findOneAuthorById(Long id);
}
