package com.example.bookauthor_app.service.base;

import com.example.bookauthor_app.exception.NotFoundException;

import java.util.List;

public interface BaseServiceInterface<T, R extends Comparable<R>> {
    void add(T t);
    void update(R r, T t) throws NotFoundException;
    List<T> getAll();
    void delete(R r) throws NotFoundException;
    T findOne(R r);
}
