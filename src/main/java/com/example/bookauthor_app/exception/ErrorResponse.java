package com.example.bookauthor_app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse {
    private HttpStatus status;
    private Date timeStamp;
    private String message;
}
