package com.example.bookauthor_app.service.author;

import com.example.bookauthor_app.dto.AuthorDTO;
import com.example.bookauthor_app.entity.Author;
import com.example.bookauthor_app.exception.NotFoundException;

import java.util.List;

public interface AuthorInterface {
    void addAuthor(AuthorDTO authorDTO);
    List<AuthorDTO> getAllAuthors();
    void updateAuthor(AuthorDTO authorDTO, Long id) throws NotFoundException;
    void removeAuthor(Long id) throws NotFoundException;
    AuthorDTO findOneAuthorById(Long id);

}
