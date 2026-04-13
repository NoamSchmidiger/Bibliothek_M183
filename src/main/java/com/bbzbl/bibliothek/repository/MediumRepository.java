package com.bbzbl.bibliothek.repository;

import com.bbzbl.bibliothek.entity.MediumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediumRepository extends JpaRepository<MediumEntity, Long> {

    Optional<MediumEntity> findByIsbn(String isbn);
}
