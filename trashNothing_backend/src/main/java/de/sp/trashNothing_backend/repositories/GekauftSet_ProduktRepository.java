package de.sp.trashNothing_backend.repositories;

import de.sp.trashNothing_backend.entities.GekauftSet_Produkt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GekauftSet_ProduktRepository extends JpaRepository<GekauftSet_Produkt, Long> {
}
