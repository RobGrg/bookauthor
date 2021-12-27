package com.example.bookauthor_app.service.book;

import com.example.bookauthor_app.dto.AuthorDTO;
import com.example.bookauthor_app.dto.BookDTO;
import com.example.bookauthor_app.entity.Author;
import com.example.bookauthor_app.entity.Book;
import com.example.bookauthor_app.exception.NotFoundException;
import com.example.bookauthor_app.repository.AuthorRepository;
import com.example.bookauthor_app.repository.BookRepository;
import com.example.bookauthor_app.util.STATUS;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class BookService implements BookInterface {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    /*
    @param bookDTO cannot be null
    bookDTO can have nested authorDTO child
    Book and Author will be added together
    Authors can be new or already in the system
    @throws IllegalArgumentException for null or empty book name or author name
     */
    @Override
    @Transactional
    public void add(BookDTO bookDTO) {
        if (bookDTO.getBookName() == null) {
            throw new IllegalArgumentException("Book Name cannot be null");
        } else if (bookDTO.getBookName().isEmpty()) {
            throw new IllegalArgumentException("Book name cannot be empty");
        }
        Book book = new Book(bookDTO.getBookName().trim().toLowerCase(), bookDTO.getIsbn(), bookDTO.getBookCategory());
        book.setStatus(STATUS.ACTIVE);
        bookDTO.getAuthors().forEach(authorDTO -> {
            if (authorDTO.getAuthorName() == null) {
                throw new IllegalArgumentException("Book Name cannot be null");
            } else if (authorDTO.getAuthorName().isEmpty()) {
                throw new IllegalArgumentException("Book Name cannot be empty");
            }
            if (authorDTO.getId() != null) {
                // check of author is present, if its present update, if not present add new author
                Optional<Author> author = authorRepository.findById(authorDTO.getId());
                if (author.isPresent()) {
                    author.get().addBook(book);
                    book.addAuthor(author.get());
                }
            } else {
                Optional<Author> author = authorRepository.findAuthorByAuthorName(authorDTO.getAuthorName());
                if (author.isPresent()) {
                    author.get().addBook(book);
                    book.addAuthor(author.get());
                } else {
                    book.addAuthor(new Author(authorDTO.getAuthorName().trim().toLowerCase(), book, STATUS.ACTIVE));
                }
            }
        });
        bookRepository.save(book);
    }

    /*
    This only updates book name, isbn and category
    Since requirement did not mention author update
    @param not null id and bookDTO
    @throws not found exception if Book is not found
     */
    @Override
    public void update(Long id, BookDTO bookDTO) throws NotFoundException {
        if (id == null || bookDTO == null) {
            throw new NullPointerException("Id and Book cannot be null");
        } else {
            if (bookRepository.findByBookName(bookDTO.getBookName()).isPresent()) {
                throw new IllegalArgumentException("Book name must be unique");
            }
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                book.setBookName(bookDTO.getBookName());
                book.setIsbn(bookDTO.getIsbn());
                book.setBookCategory(bookDTO.getBookCategory());
                bookRepository.save(book);
            } else {
                throw new NotFoundException("Book with the Id " + id + " was not found");
            }
        }
    }

    /*
    @return List of BookDTO
     */
    @Override
    public List<BookDTO> getAll() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        bookRepository.findAll().forEach(book -> {
            Set<AuthorDTO> authorDTOS = new HashSet<>();
            book.getAuthors().forEach(author -> {
                authorDTOS.add(new AuthorDTO(null, author.getAuthorName(), null));
            });
            bookDTOS.add(new BookDTO(book.getId(), book.getBookName(), book.getIsbn(), book.getBookCategory(), authorDTOS));
        });
        return bookDTOS;
    }

    /*
    This only deletes Book and its references in AuthorBook Table but author is not deleted from
    author table
    @param not null id
    @throws Not Found Exception if Book is not found
     */
    @Override
    public void delete(Long id) throws NotFoundException {
        if (id == null) {
            throw new NullPointerException("Id cannot be null");
        } else {
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                for (Author author : book.getAuthors()) {
                    author.getBooks().remove(book);
                }
                bookRepository.delete(book);
            } else {
                throw new NotFoundException("Book with the Id " + id + " was not found");
            }
        }
    }

    
    @Override
    public BookDTO findOne(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            return new BookDTO(null, book.getBookName(), book.getIsbn(), book.getBookCategory(), null);
        } else {
            return null;
        }
    }
}
