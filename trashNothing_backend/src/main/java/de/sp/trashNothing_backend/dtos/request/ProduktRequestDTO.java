package de.sp.trashNothing_backend.dtos.request;

import de.sp.trashNothing_backend.entities.enumClass.Kategorie;
import de.sp.trashNothing_backend.entities.enumClass.Zustand;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProduktRequestDTO(
        @NotBlank(message = "Titel darf nicht leer sein") String titel,
        @NotBlank(message = "Beschreibung darf nicht leer sein")
        @Size(max = 1000, message = "Die Beschreibung darf maximal 1000 Zeichen lang sein") String beschreibung,
        @Min(value = 0, message = "Die Anzahl muss mindestens 0 sein") int anzahl,
        @DecimalMin(value = "0.0", inclusive = false, message = "Der Preis muss größer als 0 sein") BigDecimal preis,
        @NotNull(message = "Zustand darf nicht leer sein") Zustand zustand,
        @NotBlank(message = "Marke darf nicht leer sein") String marke,
        boolean lieferung,
        @Size(max = 1300, message = "Die Bild-URL darf maximal 1300 Zeichen lang sein") String imgUrl,
        @Size(max = 1300, message = "Die Lösch-URL darf maximal 1300 Zeichen lang sein") String deleteUrl,
        @NotNull(message = "Kategorie darf nicht leer sein") Kategorie kategorie,
        @NotNull(message = "Benutzer ID darf nicht leer sein") Long benutzerId
) {}