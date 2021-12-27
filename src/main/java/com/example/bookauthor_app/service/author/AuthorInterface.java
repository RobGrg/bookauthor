package com.example.bookauthor_app.service.author;

import com.example.bookauthor_app.dto.AuthorDTO;
import com.example.bookauthor_app.projections.AuthorBookCount;
import com.example.bookauthor_app.exception.NotFoundException;
import com.example.bookauthor_app.service.base.BaseServiceInterface;

import java.util.List;

public interface AuthorInterface extends BaseServiceInterface<AuthorDTO, Long> {
    List<AuthorBookCount> filterAuthorByBookCount(int count);
}
