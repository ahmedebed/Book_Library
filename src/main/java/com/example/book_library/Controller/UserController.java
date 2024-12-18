package com.example.book_library.Controller;

import com.example.book_library.Service.UserService;
import com.example.book_library.User.User;
import com.example.book_library.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    @Autowired
    public  UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid UserDto userDTO) {
        try {
            userService.registerUser(userDTO);
            return ResponseEntity.ok("User registered successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/Users")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }


}
