package com.example.bookauthor_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookauthorAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookauthorAppApplication.class, args);
    }

}
