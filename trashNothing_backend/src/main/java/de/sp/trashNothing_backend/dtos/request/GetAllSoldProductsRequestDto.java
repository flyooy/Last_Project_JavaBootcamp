package de.sp.trashNothing_backend.dtos.request;

import jakarta.validation.constraints.NotNull;

public record GetAllSoldProductsRequestDto(
        @NotNull Long benutzerId
) {
}
