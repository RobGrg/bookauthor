package com.example.bookauthor_app.projections;
import lombok.Data;

/*
    This is a projection interface for Native Query in Author
 */
public interface AuthorBookCount {
    public String getAuthor();
    public long getBookCount();
}
