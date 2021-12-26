package com.example.bookauthor_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class MasterExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, new Date(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
