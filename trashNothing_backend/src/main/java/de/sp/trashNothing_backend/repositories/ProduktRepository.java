package de.sp.trashNothing_backend.repositories;


import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.entities.enumClass.Kategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduktRepository extends JpaRepository<Produkt, Long> {
    List<Produkt> findByTitelContainingIgnoreCase(String titlePart);


    List<Produkt> findByKategorie(Kategorie kategorie);


    @Query("SELECT p FROM Produkt p WHERE (:titlePart IS NULL OR LOWER(p.titel) LIKE LOWER(CONCAT('%', :titlePart, '%'))) " +
            "AND (:kategorie IS NULL OR p.kategorie = :kategorie)")
    List<Produkt> searchByTitleAndKategorie(@Param("titlePart") String titlePart,
                                            @Param("kategorie") Kategorie kategorie);

    @Query("SELECT p FROM Produkt p WHERE (:titlePart IS NULL OR LOWER(p.titel) LIKE LOWER(CONCAT('%', :titlePart, '%'))) " +
            "OR (:kategorie IS NULL OR p.kategorie = :kategorie)")
    List<Produkt> searchByTitleOderKategorie(@Param("titlePart") String titlePart,
                                             @Param("kategorie") Kategorie kategorie);
}
