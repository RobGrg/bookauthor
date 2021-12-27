package com.example.bookauthor_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class AuthorDTO {
    private Long id;
    private String authorName;
    private Set<BookDTO> books;
}
