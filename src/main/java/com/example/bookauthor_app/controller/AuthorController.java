package com.example.bookauthor_app.controller;

import com.example.bookauthor_app.controller.base.BaseControllerInterface;
import com.example.bookauthor_app.dto.AuthorDTO;
import com.example.bookauthor_app.projections.AuthorBookCount;
import com.example.bookauthor_app.exception.DefaultResponse;
import com.example.bookauthor_app.exception.NotFoundException;
import com.example.bookauthor_app.service.author.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController implements BaseControllerInterface<AuthorDTO,Long> {
    private final AuthorService authorService;

    @Operation(summary = "Add author with book written by them, Multiple")
    @PostMapping("/add")
    @Override
    public ResponseEntity<DefaultResponse> add(@Valid @RequestBody AuthorDTO authorDTO) {
        authorService.add(authorDTO);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.OK.toString()));
    }

    @Operation(summary = "Get All the authors. Note this also returns nested child.")
    @GetMapping("/getAll")
    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @Operation(summary = "Return author details by author id")
    @GetMapping("/findOne/{author_id}")
    @Override
    public ResponseEntity<AuthorDTO> findOne(@PathVariable(value = "author_id") Long authorId) throws NotFoundException {
        AuthorDTO authorDTO = authorService.findOne(authorId);
        if (authorDTO == null) {
            throw new NotFoundException("The author with the id " + authorId + " was not found");
        } else {
            return ResponseEntity.ok().body(authorDTO);
        }
    }

    @Operation(summary = "update author, requires id, updates only authorName")
    @PutMapping("/update/{author_id}")
    @Override
    public ResponseEntity<?> update(@PathVariable(value = "author_id") Long id, @Valid @RequestBody AuthorDTO authorDTO) throws NotFoundException {
        authorService.update(id,authorDTO);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.OK.toString()));
    }

    @Operation(summary = "delete author by author id. This deletes authors and details from authorbooks {authorbooks" +
            " is the many to many child table between author and book} ")
    @DeleteMapping("/delete/{author_id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable(value = "author_id") Long id) throws NotFoundException {
        authorService.delete(id);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.OK.toString()));
    }

    @Operation(summary = "filter author by book count they have written")
    @GetMapping("/filterByBookCount/{count}")
    public ResponseEntity<?> filterAuthorByBookCount(@PathVariable(name = "count") int count) {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.filterAuthorByBookCount(count));
    }

}
