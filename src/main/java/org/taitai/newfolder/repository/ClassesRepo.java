package org.taitai.newfolder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taitai.newfolder.model.entity.Classes;

import java.util.Optional;

@Repository
public interface ClassesRepo extends JpaRepository<Classes, Long> {
    Optional<Classes> findById(Long id);

}
