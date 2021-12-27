package com.example.bookauthor_app.controller.base;

import org.springframework.http.ResponseEntity;

public interface BaseControllerInterface<T,R extends Comparable<R>> {
    ResponseEntity<?> add(T t);
    ResponseEntity<?> update(R r);
    ResponseEntity<?> delete(R r);
    ResponseEntity<?> getAll();
}
