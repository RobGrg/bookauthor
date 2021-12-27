package com.example.bookauthor_app.controller.base;

import com.example.bookauthor_app.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
/*
This is the base interface for all the controllers that shares these common methods.
 */
public interface BaseControllerInterface<T, R extends Comparable<R>> {
    ResponseEntity<?> add(T t);

    ResponseEntity<?> update(R r, T t) throws NotFoundException;

    ResponseEntity<?> delete(R r) throws NotFoundException;

    ResponseEntity<?> getAll();

    ResponseEntity<?> findOne(R r) throws NotFoundException;
}
