package com.example.ob_spring_security_oauth_github.config;


import com.example.ob_spring_security_oauth_github.service.UserOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserOAuth2UserService userOAuth2UserService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/", "/login**").permitAll()
                        .anyRequest().authenticated()
                )
         /*      .oauth2Login(Customizer.withDefaults());
        return http.build();*/

                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(userOAuth2UserService)
                        )
                );
        return http.build();
    }
    }
