package de.sp.trashNothing_backend.mapper;

import de.sp.trashNothing_backend.dtos.request.AuthRequestDto;
import de.sp.trashNothing_backend.dtos.response.AuthResponseDto;
import de.sp.trashNothing_backend.entities.Benutzer;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public static Benutzer toEntity(AuthRequestDto authRequestDto) {
        if (authRequestDto == null) {
            return null;
        }
        Benutzer benutzer = new Benutzer();
        benutzer.setName(authRequestDto.name());
        benutzer.setEmail(authRequestDto.email());
        benutzer.setPassword(authRequestDto.password());
        benutzer.setPlz(authRequestDto.plz());
        benutzer.setOrte(authRequestDto.orte());
        benutzer.setAddressStrasse(authRequestDto.addressStrasse());
        benutzer.setHandynummer(authRequestDto.handynummer());
        return benutzer;
    }

    public static AuthResponseDto toResponseDto(Benutzer benutzer) {
        if (benutzer == null) {
            return null;
        }
        return new AuthResponseDto(
                benutzer.getId(),
                benutzer.getName(),
                benutzer.getEmail(),
                benutzer.getPlz(),
                benutzer.getOrte(),
                benutzer.getAddressStrasse(),
                benutzer.getHandynummer()
        );
    }
}
