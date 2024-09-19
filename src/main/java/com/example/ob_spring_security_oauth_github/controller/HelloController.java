package com.example.ob_spring_security_oauth_github.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/page1")
    public String page1(Model model){
        model.addAttribute("message", "Hello world from Spring MVC !!");
        return"page1";

    }

    @GetMapping("/page2")
    public String page2(
            Model model,
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OAuth2User user


            ){
        client.getClientRegistration().getClientName();
        user.getName();
        user.getAttributes();

        model.addAttribute("clientName",client.getClientRegistration().getClientName());
        model.addAttribute("userName",user.getName());
        model.addAttribute("userAttributes",user.getAttributes());
   /*     model.addAttribute("github_id",user.getAttributes().get());*/
        return"page2";

    }
}
