package com.example.ob_spring_security_oauth_github.service;

import com.example.ob_spring_security_oauth_github.model.User;
import com.example.ob_spring_security_oauth_github.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserRepository  userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        String githubId = oAuth2User.getAttribute("id");
        String username = oAuth2User.getAttribute("login");
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        String avatarUrl = oAuth2User.getAttribute("avatar_url");

        Optional<User> existingUser = userRepository.findByGithubId(githubId);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(username);
            user.setName(name);
            user.setEmail(email);
            user.setAvatarUrl(avatarUrl);
            userRepository.save(user);
        } else {
            User newUser = new User();
            newUser.setGithubId(githubId);
            newUser.setUsername(username);
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setAvatarUrl(avatarUrl);
            userRepository.save(newUser);
        }

        return oAuth2User;
    }

    }

