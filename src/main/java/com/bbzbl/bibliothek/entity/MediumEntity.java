package com.bbzbl.bibliothek.entity;

import com.bbzbl.bibliothek.type.MediumType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.ISBN;

@Entity
@Table(name = "media")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MediumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private MediumType type;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @ISBN
    @Column(nullable = false, unique = true)
    private String isbn;
}
