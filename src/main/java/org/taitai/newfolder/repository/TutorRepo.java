package org.taitai.newfolder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taitai.newfolder.model.entity.Tutor;

import java.util.Optional;
@Repository
public interface TutorRepo extends JpaRepository<Tutor,String> {
    Optional<Tutor> findTutorByEmail(String email);
}
