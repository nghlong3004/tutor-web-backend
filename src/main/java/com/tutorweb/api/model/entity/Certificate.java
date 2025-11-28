package com.tutorweb.api.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "certificate")
public class Certificate {
    @Id
    @MapsId
    @OneToOne
    @JoinColumn(name = "id", nullable = false, unique = true)
    private Tutor tutor;

    @Column(nullable = false , length = 200)
    private String name;

    @Column(name = "image_url", nullable = false , length = 500)
    private String imageUrl;

}
