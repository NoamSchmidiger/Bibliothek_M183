package com.bbzbl.bibliothek.dto;

import com.bbzbl.bibliothek.type.MediumType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.ISBN;

public record MediumDto(
        Long id,

        @NotNull
        MediumType type,

        @NotBlank
        @Size(max = 100)
        @Pattern(regexp = "^[a-zA-ZäöüÄÖÜß0-9]+$")
        String title,

        @NotBlank
        @Size(max = 50)
        @Pattern(regexp = "^[a-zA-ZäöüÄÖÜß]+$")
        String author,

        @NotBlank
        @ISBN(type = ISBN.Type.ISBN_13)
        String isbn
) {}
