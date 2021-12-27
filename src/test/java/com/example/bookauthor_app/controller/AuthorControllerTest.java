package com.example.bookauthor_app.controller;

import com.example.bookauthor_app.BookauthorAppApplication;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookauthorAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private static final String URL = "/api/authors";

    @Test
    void addAuthor() {
    }

    @Test
    void getAllAuthors() throws Exception {

    }

    @Test
    void getAuthor() {
    }

    @Test
    void updateAuthor() {
    }

    @Test
    void deleteAuthor() {
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