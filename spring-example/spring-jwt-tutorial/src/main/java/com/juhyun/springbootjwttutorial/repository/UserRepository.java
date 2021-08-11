package com.juhyun.springbootjwttutorial.repository;

import com.juhyun.springbootjwttutorial.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "authorities") // Eager
    Optional<User> findOneWithAuthoritiesByUsername(String username);
}
