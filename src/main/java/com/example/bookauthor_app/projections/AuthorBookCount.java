package com.example.bookauthor_app.projections;
import lombok.Data;

public interface AuthorBookCount {
    public String getAuthor();
    public long getBookCount();
}
