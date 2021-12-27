package com.example.bookauthor_app.service.book;

import com.example.bookauthor_app.dto.BookDTO;
import com.example.bookauthor_app.entity.Book;
import com.example.bookauthor_app.exception.NotFoundException;
import com.example.bookauthor_app.service.base.BaseServiceInterface;
import com.example.bookauthor_app.util.STATUS;
import jdk.jshell.Snippet;

import java.util.List;
import java.util.Optional;

public interface BookInterface extends BaseServiceInterface<BookDTO, Long> {
    @Override
    void add(BookDTO bookDTO);

    @Override
    void update(Long aLong, BookDTO bookDTO) throws NotFoundException;

    @Override
    List<BookDTO> getAll();

    @Override
    void delete(Long aLong) throws NotFoundException;

    @Override
    BookDTO findOne(Long aLong);

}
