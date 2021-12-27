package com.example.bookauthor_app.controller;

import com.example.bookauthor_app.dto.AuthorDTO;
import com.example.bookauthor_app.projections.AuthorBookCount;
import com.example.bookauthor_app.exception.DefaultResponse;
import com.example.bookauthor_app.exception.NotFoundException;
import com.example.bookauthor_app.service.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<DefaultResponse> addAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        authorService.addAuthor(authorDTO);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.OK.toString()));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/findOne/{author_id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable(value = "author_id") Long authorId) throws NotFoundException {
        AuthorDTO authorDTO = authorService.getAuthorById(authorId);
        if (authorDTO == null) {
            throw new NotFoundException("The author with the id " + authorId + " was not found");
        } else {
            return ResponseEntity.ok().body(authorDTO);
        }
    }

    @PutMapping("/update/{author_id}")
    public ResponseEntity<?> updateAuthor(@PathVariable(value = "author_id") Long id, @Valid @RequestBody AuthorDTO authorDTO) throws NotFoundException {
        authorService.updateAuthor(authorDTO, id);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.OK.toString()));
    }

    @DeleteMapping("/delete/{author_id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable(value = "author_id") Long id) throws NotFoundException {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.OK.toString()));
    }

    @GetMapping("/filterByBookCount/{count}")
    public ResponseEntity<?> filterAuthorByBookCount(@PathVariable(name = "count") int count) {
        return ResponseEntity.status(HttpStatus.FOUND).body(authorService.filterAuthorByBookCount(count));
    }

}
