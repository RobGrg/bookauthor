package com.example.bookauthor_app.service.book;

import com.example.bookauthor_app.dto.BookDTO;
import com.example.bookauthor_app.entity.Book;
import com.example.bookauthor_app.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookInterface {
    void addBook(BookDTO bookDTO);
    void updateBook(Long id, BookDTO bookDTO) throws NotFoundException;
    void deleteBook(Long id) throws NotFoundException;
    List<BookDTO> getAllBooks();
    BookDTO findBookById(Long id) throws NotFoundException;
}
