package com.bbzbl.bibliothek.controller;

import com.bbzbl.bibliothek.dto.MediumDto;
import com.bbzbl.bibliothek.service.MediumService;
import com.bbzbl.bibliothek.type.MediumType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediumController {

    private final MediumService mediumService;


    @GetMapping
    public String listMedia(Model model) {
        model.addAttribute("media", mediumService.getAllMedia());
        return "media-list";
    }

    @GetMapping("/{isbn}")
    public String viewMediumDetails(@PathVariable String isbn, Model model) {
        model.addAttribute("medium", mediumService.getMediumByIsbn(isbn));
        return "medium-details";
    }

    // --- MANAGING MEDIA (Strictly ADMIN only) ---

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showCreateForm(Model model) {
        model.addAttribute("mediumDto", new MediumDto(null, MediumType.BOOK, "", "", ""));
        return "medium-form";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createMedium(@Valid @ModelAttribute("mediumDto") MediumDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "medium-form";
        }
        mediumService.createMedium(dto);
        return "redirect:/media";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showEditForm(@PathVariable Long id, Model model) {
        // We fetch by ID to edit, but your service currently only has getMediumByIsbn.
        // For standard operations, you should add a getMediumById(Long id) to your MediumService.
        // Assuming you add that simple method:
        MediumDto existingMedium = mediumService.getMediumById(id);
        model.addAttribute("mediumDto", existingMedium);
        return "medium-form";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateMedium(@PathVariable Long id, @Valid @ModelAttribute("mediumDto") MediumDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "medium-form";
        }
        mediumService.updateMedium(id, dto);
        return "redirect:/media";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteMedium(@PathVariable Long id) {
        mediumService.deleteMedium(id);
        return "redirect:/media";
    }
}
