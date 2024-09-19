package com.example.ob_spring_security_oauth_github.config.repository;


import com.example.ob_spring_security_oauth_github.config.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByGithubId(String githubId);
    }

