package com.example.bookauthor_app.service.author;

import com.example.bookauthor_app.dto.AuthorDTO;
import com.example.bookauthor_app.dto.BookDTO;
import com.example.bookauthor_app.entity.Author;
import com.example.bookauthor_app.entity.Book;
import com.example.bookauthor_app.exception.NotFoundException;
import com.example.bookauthor_app.repository.AuthorRepository;
import com.example.bookauthor_app.repository.BookRepository;
import com.example.bookauthor_app.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class AuthorService implements AuthorInterface {
    private final AuthorRepository authorRepository;
    private final BookService bookService;

    @Override
    @Transactional
    public void addAuthor(AuthorDTO authorDTO) {
        if (authorDTO.getAuthorName() == null) {
            throw new IllegalArgumentException("Author Name cannot be null");
        } else if (authorDTO.getAuthorName().isEmpty()) {
            throw new IllegalArgumentException("Author Name cannot be empty");
        } else if (authorDTO.getBookSet() == null) {
            throw new IllegalArgumentException("Books cannot be null");
        } else if (authorDTO.getBookSet().size() == 0) {
            throw new IllegalArgumentException("Author cannot be created without book");
        }
        authorDTO.getBookSet().forEach(bookDTO -> {
            bookDTO.setAuthorDTOSet(Set.of(authorDTO));
            bookService.addBook(bookDTO);
        });
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        authorRepository.findAll().forEach(author -> {
            Set<BookDTO> bookDTOS = new HashSet<>();
            author.getBooks().forEach(book -> {
                bookDTOS.add(new BookDTO(null, book.getBookName(), book.getIsbn(), book.getBookCategory(), null));
            });
            authorDTOS.add(new AuthorDTO(author.getId(), author.getAuthorName(), bookDTOS));
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

        return authorOptional.map(author -> {
            Set<BookDTO> bookDTOS = new HashSet<>();
            author.getBooks().forEach(book -> {
                bookDTOS.add(new BookDTO(null, book.getBookName(), book.getIsbn(), book.getBookCategory(), null));
            });
            return new AuthorDTO(author.getId(), author.getAuthorName(), bookDTOS);
        }).orElse(null);
    }
}
