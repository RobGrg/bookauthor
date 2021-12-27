package com.example.bookauthor_app.service.base;

import java.util.List;

public interface BaseServiceInterface<T, R extends Comparable<R>> {
    void add(T t);
    void update(R r);
    List<T> getAll();
    void delete(R r);
    T findOne(R r);
}
