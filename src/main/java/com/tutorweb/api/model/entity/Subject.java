package com.tutorweb.api.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true , nullable = false , length = 100)
    private String subject;

    @ManyToMany(mappedBy = "subject" , fetch = FetchType.LAZY)
    private Set<Tutor> tutor;

}
