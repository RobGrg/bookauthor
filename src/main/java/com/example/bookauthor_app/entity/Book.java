package com.example.bookauthor_app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
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


}
