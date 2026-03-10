package com.bbzbl.bibliothek.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "medium")
public class Medium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MediumType typ;

    private String titel;

    private String autor;

    @Column(unique = true)
    private String isbn;

    public Medium() {
    }

    public Medium(Long id) {
        this.id = id;
    }

    public Medium(Long id, MediumType typ, String titel, String autor, String isbn) {
        this.id = id;
        this.typ = typ;
        this.titel = titel;
        this.autor = autor;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MediumType getTyp() {
        return typ;
    }

    public void setTyp(MediumType typ) {
        this.typ = typ;
    }

    public @Size(max = 100) String getTitel() {
        return titel;
    }

    public void setTitel(@Size(max = 100) String titel) {
        this.titel = titel;
    }

    public @Size(max = 50) String getAutor() {
        return autor;
    }

    public void setAutor(@Size(max = 50) String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}