package com.example.ob_spring_security_oauth_github.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/oauth2/**").permitAll() // Permitir acceso a rutas específicas
                        .anyRequest().authenticated() // Requiere autenticación para otras solicitudes
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/github")
                        .defaultSuccessUrl("/login/success", true) // Redirigir a /login/success
                // Ruta personalizada para iniciar sesión
                );

        return http.build();
    }
}