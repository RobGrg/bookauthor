package com.example.bookauthor_app.controller;

import com.example.bookauthor_app.dto.BookDTO;
import com.example.bookauthor_app.exception.DefaultResponse;
import com.example.bookauthor_app.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<?> addAuthor(@Valid @RequestBody BookDTO bookDTO){
        bookService.addBook(bookDTO);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.ACCEPTED.toString()));
    }
}
