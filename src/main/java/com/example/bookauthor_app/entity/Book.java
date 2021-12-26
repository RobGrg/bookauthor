package com.example.bookauthor_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    // Generation Type Identity makes id creation handle by the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    List<Author> authors = new ArrayList<>();

    public void addAuthor(Author author){
        authors.add(author);
    }

}
