package org.taitai.tutor_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.taitai.tutor_backend.type.RoleType;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="role")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name ="role_name", columnDefinition = "NVARCHAR(100)")
    @Getter
    @Setter
    private RoleType roleName;

    @ManyToMany(mappedBy = "roles")
    @Getter
    @Setter
    private Set<User> users = new HashSet<>();

}

