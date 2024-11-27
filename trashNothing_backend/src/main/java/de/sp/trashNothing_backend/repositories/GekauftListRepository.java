package de.sp.trashNothing_backend.repositories;

import de.sp.trashNothing_backend.entities.GekauftList;
import de.sp.trashNothing_backend.entities.GekauftSet_Produkt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GekauftListRepository extends JpaRepository<GekauftList, Long> {
}
