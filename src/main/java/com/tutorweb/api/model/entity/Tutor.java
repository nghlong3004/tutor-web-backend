package com.tutorweb.api.model.entity;

import com.tutorweb.api.type.TutorStatusType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name ="tutor_profile")
@Getter
@Setter
public class Tutor {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id" , nullable = false ,  unique = true)
    private User user;

    @Column(nullable = false , length = 15 , columnDefinition = "TEXT")
    private String bio;

    @Column(name = "hourly_rate", precision = 10 , scale = 2)
    private BigDecimal hourlyRate;

    @Column(name = "experience_years")
    private int experienceYears ;

    @Column(name ="identity_car_url" , nullable = false , length = 500 , unique = true)
    private String identityCardUrl ;

    @Column(name ="profile_status")
    private TutorStatusType profileStatus= TutorStatusType.PENDING;

    @OneToOne(mappedBy = "tutor", cascade = CascadeType.ALL)
    private Certificate certificate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tutor_subject" , joinColumns = @JoinColumn(name = "tutor_id") , inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subject;

}
