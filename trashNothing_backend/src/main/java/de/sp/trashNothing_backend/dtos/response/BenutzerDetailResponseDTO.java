package de.sp.trashNothing_backend.dtos.response;

import java.util.List;
import java.util.Set;

public record BenutzerDetailResponseDTO(
        Long id,
        String name,
        String email,
        String plz,
        String ort,
        String addressStrasse,
        String handynummer,
        Set<String> verkaufSet,
        Set<String> wunschSet,
        List<String> gekauftSet
) {

}
