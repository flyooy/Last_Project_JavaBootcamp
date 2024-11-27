package de.sp.trashNothing_backend.repositories;

import de.sp.trashNothing_backend.entities.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenutzerDetailRepository extends JpaRepository<Benutzer, Long> {

}
