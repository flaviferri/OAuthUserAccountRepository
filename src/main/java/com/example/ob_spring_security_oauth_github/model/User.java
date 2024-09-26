package com.example.ob_spring_security_oauth_github.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String githubId;
    @Column(nullable = false)
    private String login;
    private String name;
    private String email;
    private String avatarUrl;

}