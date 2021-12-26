package com.example.bookauthor_app.dto;

import com.example.bookauthor_app.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class AuthorDTO {
    private String authorName;
    private Set<Book> bookSet;
}
