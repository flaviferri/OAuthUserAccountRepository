package com.example.ob_spring_security_oauth_github.controller;

import com.example.ob_spring_security_oauth_github.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Map;

import com.example.ob_spring_security_oauth_github.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
@RestController
public class Controller {

    @Autowired
    private UserService userService;

    @GetMapping("/login/success")
    public Map<String, Object> loginSuccess(@AuthenticationPrincipal OAuth2User user) {

        if (user == null) {
            throw new RuntimeException("User not authenticated");
        }

        Map<String, Object> userAttributes = user.getAttributes();
        userService.saveOrUpdateUser(userAttributes);

      /*  ModelAndView modelAndView = new ModelAndView("redirect:http://localhost:5173/home");

        modelAndView.addObject("userAttributes", userAttributes);*/

        return userAttributes;
    }
}


