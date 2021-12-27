package com.example.bookauthor_app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "Book Author API", version = "1.0"))
public class BookauthorAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookauthorAppApplication.class, args);
    }

}
