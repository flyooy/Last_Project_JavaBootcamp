package de.sp.trashNothing_backend.dtos.response;

import de.sp.trashNothing_backend.entities.enumClass.Kategorie;
import de.sp.trashNothing_backend.entities.enumClass.Zustand;

import java.math.BigDecimal;

public record ProduktResponseDTO(
        Long id,
        String titel,
        String beschreibung,
        int anzahl,
        BigDecimal preis,
        Zustand zustand,
        String marke,
        boolean lieferung,
        String imgUrl,
        String deleteUrl,
        Kategorie kategorie,
        Long benutzerId
) {}
