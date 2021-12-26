package com.example.bookauthor_app.entity;

import com.example.bookauthor_app.util.STATUS;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_name", unique = true, nullable = false)
    @NotEmpty(message = "Please provide author name!")
    private String authorName;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();


    private STATUS status;

    public Author(String authorName, Book book, STATUS status) {
        this.authorName = authorName;
        this.books.add(book);
        this.status = status;
    }

    public void addBook(Book book) {
        books.add(book);
    }


}
