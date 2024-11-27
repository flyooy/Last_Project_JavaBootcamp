package de.sp.trashNothing_backend.repositories;

import de.sp.trashNothing_backend.entities.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
     Optional<Benutzer> findByEmail(String email);
}
