package com.example.bookauthor_app.controller;

import com.example.bookauthor_app.controller.base.BaseControllerInterface;
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
public class BookController implements BaseControllerInterface<BookDTO, Long> {


    private final BookService bookService;

    @GetMapping("/{book_id}")
    @Override
    public ResponseEntity<BookDTO> findOne(@PathVariable(value = "book_id") Long id) throws NotFoundException {
        BookDTO bookDTO = bookService.findOne(id);
        if (bookDTO == null) {
            throw new NotFoundException("Book with the Id " + id + " not found.");
        } else {
            return ResponseEntity.ok().body(bookDTO);
        }
    }

    @Override
    @PostMapping("/addBook")
    public ResponseEntity<?> add(@Valid @RequestBody BookDTO bookDTO) {
        bookService.add(bookDTO);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.ACCEPTED.toString()));
    }

    @PutMapping("/updateBook/{book_id}")
    @Override
    public ResponseEntity<?> update(@PathVariable(value = "book_id") Long id, @Valid @RequestBody BookDTO bookDTO) throws NotFoundException {
        bookService.update(id, bookDTO);
        return ResponseEntity.ok(new DefaultResponse(HttpStatus.ACCEPTED.toString()));
    }

    @DeleteMapping("/deleteBook/{book_id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable(value = "book_id") Long id) throws NotFoundException {
        bookService.delete(id);
        return ResponseEntity.ok(new DefaultResponse(HttpStatus.OK.toString()));
    }

    @GetMapping("/getAllBooks")
    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAll());
    }
}
