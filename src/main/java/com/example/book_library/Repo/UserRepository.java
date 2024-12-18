package com.example.book_library.Repo;

import com.example.book_library.Enums.Roles;
import com.example.book_library.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query to find a user by email (used for login and registration)
    Optional<User> findByEmail(String email);

}
