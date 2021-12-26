package com.example.bookauthor_app.controller;

import com.example.bookauthor_app.dto.BookDTO;
import com.example.bookauthor_app.exception.DefaultResponse;
import com.example.bookauthor_app.exception.NotFoundException;
import com.example.bookauthor_app.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<?> addAuthor(@Valid @RequestBody BookDTO bookDTO) {
        bookService.addBook(bookDTO);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.ACCEPTED.toString()));
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookDTO> findBookById(@PathVariable(value = "book_id") Long id) throws NotFoundException {
        BookDTO bookDTO = bookService.findBookById(id);
        if (bookDTO == null) {
            throw new NotFoundException("Book with the Id " + id + " not found.");
        } else {
            return ResponseEntity.ok().body(bookDTO);
        }
    }

    @PutMapping("/updateBook/{book_id}")
    public ResponseEntity<?> updateBook(@PathVariable(value = "book_id") Long id, @Valid @RequestBody BookDTO bookDTO) throws NotFoundException {
        bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(new DefaultResponse(HttpStatus.ACCEPTED.toString()));
    }

    @DeleteMapping("/deleteBook/{book_id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "book_id") Long id) throws NotFoundException {
        bookService.deleteBook(id);
        return ResponseEntity.ok(new DefaultResponse(HttpStatus.OK.toString()));
    }

    @GetMapping("/getAllBooks")
    public List<BookDTO> getAllBooks(){
        return bookService.getAllBooks();
    }
}
