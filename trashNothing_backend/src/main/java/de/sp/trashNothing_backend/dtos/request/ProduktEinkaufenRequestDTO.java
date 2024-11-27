package de.sp.trashNothing_backend.dtos.request;


import de.sp.trashNothing_backend.entities.enumClass.Kategorie;
import de.sp.trashNothing_backend.entities.enumClass.Zustand;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProduktEinkaufenRequestDTO(
        @NotNull(message = "benutzerId darf nicht null sein")
        Long benutzerId,

        @NotNull(message = "produkt_id darf nicht null sein")
        Long produktId
) {
}