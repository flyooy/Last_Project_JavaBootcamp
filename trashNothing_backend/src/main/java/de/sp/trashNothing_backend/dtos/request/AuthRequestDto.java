package de.sp.trashNothing_backend.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AuthRequestDto(
        @NotBlank(message = "Name darf nicht leer sein") String name,
        @Email(message = "Ungültige E-Mail-Adresse") @NotBlank(message = "E-Mail darf nicht leer sein") String email,
        @NotBlank(message = "Passwort darf nicht leer sein") @Size(min = 8, message = "Das Passwort muss mindestens 8 Zeichen lang sein") String password,
        @NotBlank(message = "PLZ darf nicht leer sein") @Pattern(regexp = "\\d{5}", message = "PLZ muss aus 5 Ziffern bestehen") String plz,
        @NotBlank(message = "Orte darf nicht leer sein") String orte,
        @NotBlank(message = "Straße darf nicht leer sein") String addressStrasse,
        @Pattern(regexp = "\\+?\\d{10,15}", message = "Ungültige Handynummer") String handynummer
) {
}