package com.example.bookauthor_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
    This is a Resources Not Found Exception
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }
}
