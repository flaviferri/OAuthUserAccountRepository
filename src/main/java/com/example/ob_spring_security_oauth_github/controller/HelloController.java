package com.example.ob_spring_security_oauth_github.controller;

import com.example.ob_spring_security_oauth_github.model.User;
import com.example.ob_spring_security_oauth_github.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/login/success")
    public RedirectView loginSuccess(
            @AuthenticationPrincipal OAuth2User user) {

        if (user == null) {
            // Si el usuario no está autenticado, puedes redirigirlo a una página de error
            return new RedirectView("/error");
        }

        // Guardar los datos del usuario en la base de datos
        Map<String, Object> userAttributes = user.getAttributes();
        userService.saveOrUpdateUser(userAttributes);

        // Redirigir a la página de inicio del frontend después de guardar al usuario
        return new RedirectView("http://localhost:5173/home"); // Cambia la URL a la página de tu frontend
    }
}

/*


package com.example.ob_spring_security_oauth_github.controller;
import com.example.ob_spring_security_oauth_github.model.User;
import com.example.ob_spring_security_oauth_github.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    // Endpoint para page1
    @GetMapping("/page")
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

*/
/*    @GetMapping("/api/user")
    @ResponseBody
    public Map<String, Object> getUser(
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OAuth2User user
    ) {
        if (user == null || client == null) {
            return Map.of("error", "Usuario no autenticado");
        }

        Map<String, Object> userAttributes = user.getAttributes();
        User savedUser = userService.saveOrUpdateUser(userAttributes);

        return Map.of(
                "clientName", client.getClientRegistration().getClientName(),
                "userName", savedUser.getName() != null ? savedUser.getName() : "Nombre no disponible",
                "userAttributes", userAttributes
        );
    }*//*

}
*/
