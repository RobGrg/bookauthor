package com.example.bookauthor_app.controller;

import com.example.bookauthor_app.controller.base.BaseControllerInterface;
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
public class AuthorController implements BaseControllerInterface<AuthorDTO,Long> {
    private final AuthorService authorService;

    @PostMapping("/add")
    @Override
    public ResponseEntity<DefaultResponse> add(@Valid @RequestBody AuthorDTO authorDTO) {
        authorService.add(authorDTO);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.OK.toString()));
    }

    @GetMapping("/getAll")
    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

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

    @PutMapping("/update/{author_id}")
    @Override
    public ResponseEntity<?> update(@PathVariable(value = "author_id") Long id, @Valid @RequestBody AuthorDTO authorDTO) throws NotFoundException {
        authorService.update(id,authorDTO);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.OK.toString()));
    }

    @DeleteMapping("/delete/{author_id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable(value = "author_id") Long id) throws NotFoundException {
        authorService.delete(id);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.OK.toString()));
    }

    @GetMapping("/filterByBookCount/{count}")
    public ResponseEntity<?> filterAuthorByBookCount(@PathVariable(name = "count") int count) {
        return ResponseEntity.status(HttpStatus.FOUND).body(authorService.filterAuthorByBookCount(count));
    }

}
