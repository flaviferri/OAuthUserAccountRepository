package com.example.ob_spring_security_oauth_github.model;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "github_id")
    private String githubId;
    private String username;
    private String name;
    private String email;
    private String avatarUrl;

    public User() {

    }
}
