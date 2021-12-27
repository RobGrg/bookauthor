package com.example.bookauthor_app.entity;

import com.example.bookauthor_app.util.STATUS;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book extends BaseEntity{

    @Column(name = "book_name", nullable = false, unique = true)
    @NotEmpty(message = "Please provide Book Name!")
    @Size(min = 5, max = 50)
    private String bookName;

    @Column(name = "isbn", nullable = true)
    @Size(min = 10, max = 13)
    private String isbn;

    @Column(name = "book_category", nullable = true)
    private String bookCategory;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE}
                , fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonIgnore
    Set<Author> authors = new HashSet<>();

    @Column(name = "status",nullable = false)
    private STATUS status;

    public Book(String bookName, String isbn, String bookCategory) {
        this.bookName = bookName;
        this.isbn = isbn;
        this.bookCategory = bookCategory;
    }

    public void addAuthor(Author author){
        authors.add(author);
    }

}
