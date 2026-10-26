package edu.bellevue.cis530.week7.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof OAuth2User user) {
            String name = user.getAttribute("name") != null
                    ? user.getAttribute("name") : user.getAttribute("login");
            model.addAttribute("name", name);
            model.addAttribute("email", user.getAttribute("email"));
            String picture = user.getAttribute("picture") != null
                    ? user.getAttribute("picture") : user.getAttribute("avatar_url");
            model.addAttribute("picture", picture);
        } else {
            model.addAttribute("name", authentication.getName());
        }
        return "profile";
    }
}