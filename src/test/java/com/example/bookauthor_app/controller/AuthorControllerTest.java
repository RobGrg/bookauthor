package com.example.bookauthor_app.controller;

import com.example.bookauthor_app.BookauthorAppApplication;
import com.example.bookauthor_app.dto.AuthorDTO;
import com.example.bookauthor_app.dto.BookDTO;
import com.example.bookauthor_app.projections.AuthorBookCount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookauthorAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private static final String URL = "/api/authors";

    @Test
    void addAuthor() {
        HttpHeaders headers = new HttpHeaders();
        // give authorName unique
        String authorName = "Electronic";
        String bookName = "Fragger Concept";
        AuthorDTO authorDTO = new AuthorDTO(null, authorName, Set.of(new BookDTO(null, bookName, null, null, null)));
        ResponseEntity<AuthorDTO> response = testRestTemplate.postForEntity(URL + "/add",
                authorDTO, AuthorDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllAuthors() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(URL + "/getAll",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    void filterAuthorByBookCount() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(URL + "/filterByBookCount/1",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
//        assertEquals(response.getStatusCode(),ResponseEntity.status(HttpStatus.FOUND));
    }
}