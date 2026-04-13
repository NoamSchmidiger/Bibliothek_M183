package com.bbzbl.bibliothek.dto;

import com.bbzbl.bibliothek.type.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDto (
        Long id,

        @NotBlank
        @Size(min = 3, max = 20)
        @Pattern(regexp = "^[a-zA-ZäöüÄÖÜß]+$")
        String firstName,

        @NotBlank
        @Size(min = 3, max = 40)
        @Pattern(regexp = "^[a-zA-ZäöüÄÖÜß]+$")
        String lastName,

        @NotBlank
        String email,

        @NotBlank
        @Size(min = 3, max = 20)
        @Pattern(regexp = "^[a-zA-Z0-9]+$")
        String username,

        @NotBlank
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$")
        String password,

        @Pattern(regexp = "^$|^\\+?[0-9]+$")
        String telNumber,

        UserRole role
) {}
