package org.taitai.tutor_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

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
    @Column(name= "status", columnDefinition ="VARCHAR(100)")
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @Getter
    @Setter
    private User user;
}
