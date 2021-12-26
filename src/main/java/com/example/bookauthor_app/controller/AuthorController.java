package com.example.bookauthor_app.controller;

import com.example.bookauthor_app.dto.AuthorDTO;
import com.example.bookauthor_app.exception.DefaultResponse;
import com.example.bookauthor_app.exception.NotFoundException;
import com.example.bookauthor_app.service.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/addAuthor")
    public ResponseEntity<DefaultResponse> addAuthor(@Valid @RequestBody AuthorDTO authorDTO){
        authorService.addAuthor(authorDTO);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.OK));
    }

    @GetMapping("/getAllAuthors")
    public List<AuthorDTO> authorDTOList(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/findOneAuthor/{author_id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable(value = "author_id") Long authorId) throws NotFoundException {
        AuthorDTO authorDTO = authorService.findOneAuthorById(authorId);
        if (authorDTO == null){
            throw new NotFoundException("The author with the id "+authorId+" was not found");
        }else{
            return ResponseEntity.ok().body(authorDTO);
        }
    }

}
