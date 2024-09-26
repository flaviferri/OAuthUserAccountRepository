package com.example.ob_spring_security_oauth_github.controller;
import com.example.ob_spring_security_oauth_github.config.model.User;
import com.example.ob_spring_security_oauth_github.config.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    // Endpoint para page1
    @GetMapping("/page1")
    public String page1(Model model) {
        model.addAttribute("message", "Bienvenido a la página segura");
        return "page1";
    }


    @GetMapping("/page2")
    public String page2(
            Model model,
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OAuth2User user
    ) {
        if (user == null || client == null) {
            return "error";
        }

        Map<String, Object> userAttributes = user.getAttributes();

        User savedUser = userService.saveOrUpdateUser(userAttributes);

        model.addAttribute("clientName", client.getClientRegistration().getClientName());
        model.addAttribute("userName", savedUser.getName() != null ? savedUser.getName() : "Nombre no disponible");
        model.addAttribute("userAttributes", userAttributes);

        return "page2";
    }
}
