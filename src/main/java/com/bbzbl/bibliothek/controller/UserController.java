package com.bbzbl.bibliothek.controller;

import com.bbzbl.bibliothek.dto.UserDto;
import com.bbzbl.bibliothek.service.UserService;
import com.bbzbl.bibliothek.type.UserRole;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // --- PUBLIC ENDPOINTS ---

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto(null, "", "", "", "", "", "", UserRole.USER));
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDto") UserDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.createUser(dto);
        return "redirect:/login?registered";
    }

    // --- SECURED ENDPOINTS ---

    @GetMapping("/profile")
    public String showProfile(Model model, Authentication authentication) {
        UserDto currentUser = userService.getUserByUsername(authentication.getName());
        model.addAttribute("userDto", currentUser);
        return "profile";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String listAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users-list";
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or @securityService.isSameUser(#id, authentication.name)")
    public String editUser(@PathVariable Long id, @Valid @ModelAttribute("userDto") UserDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "profile";
        }
        userService.updateUser(id, dto);
        return "redirect:/profile?updated";
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or @securityService.isSameUser(#id, authentication.name)")
    public String deleteUser(@PathVariable Long id, HttpServletRequest request) throws ServletException {
        userService.deleteUser(id);
        request.logout(); // Instantly kill the session
        return "redirect:/login?logout";
    }
}
