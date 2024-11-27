package de.sp.trashNothing_backend.mapper;

import de.sp.trashNothing_backend.dtos.response.BenutzerDetailResponseDTO;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.Produkt;

import java.util.stream.Collectors;

public class BenutzerDetailMapper {
    public static BenutzerDetailResponseDTO toDto(Benutzer benutzer) {
        return new BenutzerDetailResponseDTO(
                benutzer.getId(),
                benutzer.getName(),
                benutzer.getEmail(),
                benutzer.getPlz(),
                benutzer.getOrte(),
                benutzer.getAddressStrasse(),
                benutzer.getHandynummer(),
                benutzer.getVerkaufSet() != null ? benutzer.getVerkaufSet().stream()
                        .map(Produkt::getTitel)
                        .collect(Collectors.toSet()) : null,
                benutzer.getWunschSet() != null ? benutzer.getWunschSet().stream()
                        .map(wunsch -> wunsch.getId().toString())
                        .collect(Collectors.toSet()) : null,
                benutzer.getGekauftSet() != null ? benutzer.getGekauftSet().stream()
                        .map(gekauft -> gekauft.getId().toString())
                        .collect(Collectors.toList()) : null
        );
    }
}
