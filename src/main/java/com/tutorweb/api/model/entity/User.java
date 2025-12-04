package com.tutorweb.api.model.entity;

import com.tutorweb.api.type.RoleType;
import com.tutorweb.api.type.UserStatusType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.tutorweb.api.type.UserStatusType.ACTIVE;
@Entity
@Table(name = "user_app")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true , length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false , length = 100)
    private String fullName;

    @Column(nullable = false , unique = true , length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false , length = 20)
    private RoleType role = RoleType.USER;

    @Column(nullable = false , length = 20)
    @Enumerated(EnumType.STRING)
    private UserStatusType status = ACTIVE;

    private LocalDateTime created_at = LocalDateTime.now();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Tutor tutor;

}
