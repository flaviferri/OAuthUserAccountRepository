/*
package com.example.ob_spring_security_oauth_github.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class OAuth2Controller {

    private final OAuth2AuthorizedClientService clientService;

    @Autowired
    public OAuth2Controller(OAuth2AuthorizedClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/login/oauth2/code/{provider}")
    public RedirectView loginSuccess(@PathVariable String provider, OAuth2AuthenticationToken authenticationToken,
                                     @AuthenticationPrincipal OAuth2User oauthUser) {
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(),
                authenticationToken.getName()
        );

        // Obtener detalles del usuario autenticado
        String username = oauthUser.getAttribute("login"); // GitHub devuelve el login como "login"
        String email = oauthUser.getAttribute("email"); // El correo puede ser opcional si no está en el scope

        // Aquí puedes almacenar los detalles del usuario en tu base de datos
        // Por ejemplo, guarda el nombre de usuario y el email
        // Puedes también generar un token JWT si necesitas autenticar más adelante con JWT.

        // Redirigir a una página de éxito de login
        return new RedirectView("/login-success");
    }
}*/
