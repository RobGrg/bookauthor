package com.example.bookauthor_app.service.book;

import com.example.bookauthor_app.dto.BookDTO;
import com.example.bookauthor_app.entity.Author;
import com.example.bookauthor_app.entity.Book;
import com.example.bookauthor_app.repository.AuthorRepository;
import com.example.bookauthor_app.repository.BookRepository;
import com.example.bookauthor_app.util.STATUS;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService implements BookInterface {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public void addBook(BookDTO bookDTO) {
        if (bookDTO.getBookName() == null) {
            throw new IllegalArgumentException("Book Name cannot be null");
        } else if (bookDTO.getBookName().isEmpty()) {
            throw new IllegalArgumentException("Book name cannot be empty");
        }
        Book book = new Book(bookDTO.getBookName().trim().toLowerCase(), bookDTO.getIsbn(), bookDTO.getBookCategory());
        book.setStatus(STATUS.ACTIVE);
        bookDTO.getAuthorDTOSet().forEach(authorDTO -> {
            if (authorDTO.getId() != null) {
                Optional<Author> author = authorRepository.findById(authorDTO.getId());
                if (author.isPresent()) {
                    author.get().addBook(book);
                    book.addAuthor(author.get());
                }
            } else {
                if (authorDTO.getAuthorName() == null) {
                    throw new IllegalArgumentException("Book Name cannot be null");
                } else if (authorDTO.getAuthorName().isEmpty()) {
                    throw new IllegalArgumentException("Book Name cannot be empty");
                }
                book.addAuthor(new Author(authorDTO.getAuthorName().trim().toLowerCase(), book, STATUS.ACTIVE));
            }
        });
        bookRepository.save(book);
    }
}
