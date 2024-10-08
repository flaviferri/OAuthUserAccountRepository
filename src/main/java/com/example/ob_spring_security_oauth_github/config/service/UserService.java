package com.example.ob_spring_security_oauth_github.config.service;



import com.example.ob_spring_security_oauth_github.config.model.User;
import com.example.ob_spring_security_oauth_github.config.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdateUser(Map<String, Object> userAttributes) {
        String githubId = userAttributes.get("id").toString();
        String login = (String) userAttributes.get("login");
        String name = (String) userAttributes.get("name");
        String email = (String) userAttributes.get("email");
        String avatarUrl = (String) userAttributes.get("avatar_url");

        Optional<User> existingUser = userRepository.findByGithubId(githubId);

        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            User user = new User();
            user.setGithubId(githubId);
            user.setLogin(login);
            user.setName(name);
            user.setEmail(email);
            user.setAvatarUrl(avatarUrl);

            return userRepository.save(user);
        }
    }
}