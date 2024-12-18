package com.example.book_library.Service;

import com.example.book_library.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepo bookRepo;
    @Autowired
    public BookService(BookRepo bookRepo){
        this.bookRepo=bookRepo;

    }
}
