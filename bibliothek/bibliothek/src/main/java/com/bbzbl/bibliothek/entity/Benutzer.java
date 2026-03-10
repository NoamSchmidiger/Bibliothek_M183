package com.bbzbl.bibliothek.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "benutzer")
public class Benutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vorname;

    private String nachname;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String benutzername;

    private String passwortHash;

    private String telefonnummer;

    private String rolle = "USER";

    public Benutzer() {
    }

    public Benutzer(Long id) {
        this.id = id;
    }

    public Benutzer(Long id, String vorname, String nachname, String email, String benutzername, String passwortHash, String telefonnummer, String rolle) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.benutzername = benutzername;
        this.passwortHash = passwortHash;
        this.telefonnummer = telefonnummer;
        this.rolle = rolle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswortHash() {
        return passwortHash;
    }

    public void setPasswortHash(String passwortHash) {
        this.passwortHash = passwortHash;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
}