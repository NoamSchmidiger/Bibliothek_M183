package com.bbzbl.bibliothek.entity;

import com.bbzbl.bibliothek.type.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 40)
    private String lastName;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    private String telNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
