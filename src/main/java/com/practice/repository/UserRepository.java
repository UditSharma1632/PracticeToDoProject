package com.practice.repository;

import com.practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);
    Boolean existsByEmail(String email);

    Optional<User> findByUserNameOrEmail(String username, String email);
}
