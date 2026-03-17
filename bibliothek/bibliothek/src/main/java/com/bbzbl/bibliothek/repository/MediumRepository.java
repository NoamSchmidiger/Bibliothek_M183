package com.bbzbl.bibliothek.repository;

import com.bbzbl.bibliothek.entity.Medium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediumRepository extends JpaRepository<Medium, Long> {
}
