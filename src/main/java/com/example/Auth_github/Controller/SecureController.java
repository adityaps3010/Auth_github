package com.example.Auth_github.Controller;

import java.io.PrintStream;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
public class SecureController {

    @GetMapping("/")
    public String securePage(OAuth2AuthenticationToken token, Model model) {
        // Retrieve user details

        System.out.println("token is "+token.getAuthorizedClientRegistrationId());
        OAuth2User user = token.getPrincipal();
        String ID = user.getName();
        String username=user.getAttribute("login");// or use user.getAttribute("login") if available
        String avatarUrl = user.getAttribute("avatar_url");
        String profileUrl = user.getAttribute("html_url");
        Integer publicRepos = user.getAttribute("public_repos");
        Integer publicGists = user.getAttribute("public_gists");
        Integer followers = user.getAttribute("followers");
        Integer following = user.getAttribute("following");
        String createdAt = user.getAttribute("created_at");
        String updatedAt = user.getAttribute("updated_at");


        // Convert created_at and updated_at to a readable format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
                .withZone(ZoneId.systemDefault());
        String formattedCreatedAt = formatter.format(Instant.parse(createdAt));
        String formattedUpdatedAt = formatter.format(Instant.parse(updatedAt));


        // Add attributes to the model
        model.addAttribute("username", username);
        model.addAttribute("ID", ID);
        model.addAttribute("avatarUrl", avatarUrl);
        model.addAttribute("profileUrl", profileUrl);
        model.addAttribute("publicRepos", publicRepos);
        model.addAttribute("publicGists", publicGists);
        model.addAttribute("followers", followers);
        model.addAttribute("following", following);
        model.addAttribute("createdAt", formattedCreatedAt);
        model.addAttribute("updatedAt", formattedUpdatedAt);

        return "secure";
    }
}

