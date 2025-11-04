package org.taitai.newfolder.model.entity;

import jakarta.persistence.*;

import lombok.Getter;

import lombok.Setter;
import org.taitai.newfolder.enumm.RoleName;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="role")

public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name ="role_name", columnDefinition = "NVARCHAR(100)")
    @Getter
    @Setter
    private RoleName roleName;

    @ManyToMany(mappedBy = "roles")
    @Getter
    @Setter
    private Set<User> users = new HashSet<>();

}
