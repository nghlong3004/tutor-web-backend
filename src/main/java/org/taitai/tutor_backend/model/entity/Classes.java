package org.taitai.tutor_backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.taitai.tutor_backend.type.ApplyStatus;

@Entity
@Table
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_class")
    @Getter
    private Long id ;
    @Column(name = "username", columnDefinition = "VARCHAR(100)")
    @Getter
    @Setter
    private String username;
    @Column(name = "description", columnDefinition = "VARCHAR(100)")
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private ApplyStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @Getter
    @Setter
    private User user;
}
