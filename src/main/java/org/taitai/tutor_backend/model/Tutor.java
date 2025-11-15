package org.taitai.tutor_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tutor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tutor {
    @Id
    @Column(name = "name")
    private String name;
    @OneToOne
    @JoinColumn(name = "tutor_id", referencedColumnName = "id" )
    private User user;
    @Column(name = "email" , columnDefinition = "VARCHAR(100)")
    private String email;
    @Column(name = "subject" , columnDefinition = "VARCHAR(100)")
    private String subject;
}

