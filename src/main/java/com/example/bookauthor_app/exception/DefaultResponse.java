package com.example.bookauthor_app.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
public class DefaultResponse {
    private HttpStatus status;
}
