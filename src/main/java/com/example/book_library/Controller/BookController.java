package com.example.book_library.Controller;

import com.example.book_library.Service.BookService;
import com.example.book_library.Service.UserService;
import com.example.book_library.User.Book;
import com.example.book_library.dto.BookDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class BookController {
    private final UserService userService;
    private final BookService bookService;
    @Autowired
    public  BookController(BookService bookService,UserService userService){
        this.bookService=bookService;
        this.userService=userService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addBook(
            @RequestParam Long userId,
            @Valid @RequestBody BookDto bookDTO,
            BindingResult result
    ) {
        // Validate the incoming BookDTO
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> {
                errors.append(error.getDefaultMessage()).append(". ");
            });
            return ResponseEntity.badRequest().body(errors.toString());
        }

        try {
            // Check if the user is an AUTHOR and add the book
            userService.addBook(userId, bookDTO);
            return ResponseEntity.ok("Book added successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
