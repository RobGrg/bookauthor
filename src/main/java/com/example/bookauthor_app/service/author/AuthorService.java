package com.example.bookauthor_app.service.author;

import com.example.bookauthor_app.dto.AuthorDTO;
import com.example.bookauthor_app.entity.Author;
import com.example.bookauthor_app.exception.NotFoundException;
import com.example.bookauthor_app.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService implements AuthorInterface {
    private final AuthorRepository authorRepository;

    @Override
    public void addAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setAuthorName(authorDTO.getAuthorName());
        authorDTO.getBookSet().forEach(book -> {
            if (book.getId() == null){

            }
        });
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        authorRepository.findAll().forEach(author -> {
            authorDTOS.add(new AuthorDTO(author.getAuthorName(), author.getBooks()));
        });
        return authorDTOS;
    }

    @Override
    public void updateAuthor(AuthorDTO authorDTO) {

    }

    @Override
    public void removeAuthor(AuthorDTO authorDTO) {

    }

    @Override
    public AuthorDTO findOneAuthorById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        return authorOptional.map(author -> new AuthorDTO(author.getAuthorName(), author.getBooks())).orElse(null);
    }
}
