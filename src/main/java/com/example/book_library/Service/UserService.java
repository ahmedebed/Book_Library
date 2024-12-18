package com.example.book_library.Service;

import com.example.book_library.Enums.Roles;
import com.example.book_library.Repo.BookRepo;
import com.example.book_library.Repo.UserRepository;
import com.example.book_library.User.Book;
import com.example.book_library.User.User;
import com.example.book_library.dto.BookDto;
import com.example.book_library.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final BookRepo bookRepo;
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository,BookRepo bookRepo){
        this.userRepository=userRepository;
        this.bookRepo =bookRepo;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User registerUser(UserDto userDTO) {
        // Check if the email already exists
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use.");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword()); // In a real-world app, hash the password before saving
        user.setRoles(user.getRoles()); // Default role for registration

        return userRepository.save(user);
    }
    public void addBook(Long userId, BookDto bookDTO) {
        // Find the user by ID
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user's role is AUTHOR
        if (!"AUTHOR".equalsIgnoreCase(user.getRoles().name())) {
            throw new RuntimeException("Only users with AUTHOR role can add books");
        }

        // Create a Book entity from the BookDTO
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setDescription(bookDTO.getDescription());
        book.setAuthor(user);

        // Save the book to the repository
        bookRepo.save(book);
    }
}
