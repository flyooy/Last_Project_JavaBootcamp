package de.sp.trashNothing_backend.dtos.request;

import jakarta.validation.constraints.NotNull;

public record ProduktVerkauftRequestDto(
        @NotNull Long benutzerId,
        @NotNull Long produktId
) {
}
