package com.example.bookauthor_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String bookName;
    private String isbn;
    private String bookCategory;
    private Set<AuthorDTO> authors;

    boolean isNew() {
        return id == null;
    }

}
