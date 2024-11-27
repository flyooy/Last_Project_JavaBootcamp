package de.sp.trashNothing_backend.dtos.request;

import de.sp.trashNothing_backend.entities.enumClass.Kategorie;
import de.sp.trashNothing_backend.entities.enumClass.Zustand;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record ProduktRequestDTOWithImage(
        @NotBlank(message = "Titel darf nicht leer sein") String titel,
        @NotBlank(message = "Beschreibung darf nicht leer sein")
        @Size(max = 1000, message = "Die Beschreibung darf maximal 1000 Zeichen lang sein") String beschreibung,
        @Min(value = 0, message = "Die Anzahl muss mindestens 0 sein") Integer anzahl,
        @DecimalMin(value = "0.0", inclusive = false, message = "Der Preis muss größer als 0 sein") BigDecimal preis,
        @NotNull(message = "Zustand darf nicht leer sein") Zustand zustand,
        @NotBlank(message = "Marke darf nicht leer sein") String marke,
        boolean lieferung,
        @NotNull(message = "Kategorie darf nicht leer sein") Kategorie kategorie,
        @NotNull(message = "Benutzer ID darf nicht leer sein") Long benutzerId,
        @NotNull(message = "Bilddatei darf nicht leer sein") MultipartFile imgFile
) {
    public ProduktRequestDTOWithImage {
        if (imgFile.isEmpty()) {
            throw new IllegalArgumentException("Bilddatei darf nicht leer sein");
        }
    }
}