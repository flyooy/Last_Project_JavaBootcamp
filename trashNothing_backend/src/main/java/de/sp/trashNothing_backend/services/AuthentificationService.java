package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.request.AuthRequestDto;
import de.sp.trashNothing_backend.dtos.response.AuthResponseDto;
import de.sp.trashNothing_backend.dtos.response.TokenResponseDto;
import org.springframework.security.core.Authentication;

public interface AuthentificationService {
    AuthResponseDto signUp(AuthRequestDto dto);

    TokenResponseDto token(Authentication authentication);
}
