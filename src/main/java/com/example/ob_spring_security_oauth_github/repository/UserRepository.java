package com.example.ob_spring_security_oauth_github.repository;

import com.example.ob_spring_security_oauth_github.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE github_id = :githubId", nativeQuery = true)
    Optional<User> findByGithubId(@Param("githubId") String githubId);
}