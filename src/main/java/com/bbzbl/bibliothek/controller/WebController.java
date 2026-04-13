package com.bbzbl.bibliothek.controller;

import com.bbzbl.bibliothek.dto.UserDto;
import com.bbzbl.bibliothek.service.MediumService;
import com.bbzbl.bibliothek.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final UserService userService;
    private final MediumService mediumService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        String currentUsername = authentication.getName();
        UserDto currentUser = userService.getUserByUsername(currentUsername);

        model.addAttribute("user", currentUser);
        model.addAttribute("allMedia", mediumService.getAllMedia());

        return "dashboard";
    }
}
