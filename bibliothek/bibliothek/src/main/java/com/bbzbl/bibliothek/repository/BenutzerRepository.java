package com.bbzbl.bibliothek.repository;

import com.bbzbl.bibliothek.entity.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
}
