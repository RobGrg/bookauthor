package com.example.bookauthor_app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
/*
This will default Response.
Response Details needed in future can be added here.
 */
public class DefaultResponse {
    private String status;
}
