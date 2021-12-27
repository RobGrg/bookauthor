package com.example.bookauthor_app.service.author;

import com.example.bookauthor_app.dto.AuthorDTO;
import com.example.bookauthor_app.dto.BookDTO;
import com.example.bookauthor_app.entity.Author;
import com.example.bookauthor_app.projections.AuthorBookCount;
import com.example.bookauthor_app.entity.Book;
import com.example.bookauthor_app.exception.NotFoundException;
import com.example.bookauthor_app.repository.AuthorRepository;
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

    /*
    @param not null authorDTO, it can have multiple child bookDTO
    Books as child must be new for now
    @throws Illegal Argument Exception for null violation and empty string
     */
    @Override
    @Transactional
    public void add(AuthorDTO authorDTO) {
        if (authorDTO == null) {
            throw new IllegalArgumentException("The Author cannot be null.");
        }
        if (authorDTO.getAuthorName() == null && authorDTO.getBooks() == null) {
            throw new IllegalArgumentException("The Author Name or BookSet cannot be null.");
        } else if (authorDTO.getAuthorName().isEmpty()) {
            throw new IllegalArgumentException("The Author Name cannot be empty.");
        } else if (authorDTO.getBooks().size() == 0) {
            throw new IllegalArgumentException("The Author cannot be created without book.");
        }
        authorDTO.getBooks().forEach(bookDTO -> {
            bookDTO.setAuthors(Set.of(authorDTO));
            bookService.add(bookDTO);
        });
    }

    /*
    Updates author name only
     */
    @Override
    public void update(Long id, AuthorDTO authorDTO) throws NotFoundException {
        if (authorDTO == null) {
            throw new NullPointerException("The Author cannot be null.");
        } else if ((id != null) && (authorDTO.getAuthorName() != null || !authorDTO.getAuthorName().isEmpty())) {
            Optional<Author> authorOptional = authorRepository.findById(id);
            if (authorOptional.isPresent()) {
                if (authorRepository.findAuthorByAuthorName(authorDTO.getAuthorName()).isPresent()) {
                    throw new IllegalArgumentException("The Author name must be unique.");
                } else {
                    authorOptional.get().setAuthorName(authorDTO.getAuthorName());
                    authorRepository.save(authorOptional.get());
                }
            } else {
                throw new NotFoundException("The Author with the Id " + id + " was not found.");
            }
        }
    }

    @Override
    public List<AuthorDTO> getAll() {
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
    public void delete(Long id) throws NotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            for (Book book : author.getBooks()) {
                book.getAuthors().remove(author);
            }
            authorRepository.delete(author);
        } else {
            throw new NotFoundException("The Author with the Id " + id + " was not found.");
        }
    }

    @Override
    public AuthorDTO findOne(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);

        return authorOptional.map(author -> {
            Set<BookDTO> bookDTOS = new HashSet<>();
            author.getBooks().forEach(book -> {
                bookDTOS.add(new BookDTO(null, book.getBookName(), book.getIsbn(), book.getBookCategory(), null));
            });
            return new AuthorDTO(author.getId(), author.getAuthorName(), bookDTOS);
        }).orElse(null);
    }


    @Override
    public List<AuthorBookCount> filterAuthorByBookCount(int count) {
        return authorRepository.findAuthorBookCount(count);
    }
}
