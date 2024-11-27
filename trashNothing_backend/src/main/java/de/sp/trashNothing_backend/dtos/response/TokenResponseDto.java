package de.sp.trashNothing_backend.dtos.response;

public record TokenResponseDto(
        String token,
        Long benutzerId
) {
}

