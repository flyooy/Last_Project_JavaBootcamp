package de.sp.trashNothing_backend.services.impl;

import de.sp.trashNothing_backend.dtos.request.AuthRequestDto;
import de.sp.trashNothing_backend.dtos.response.AuthResponseDto;
import de.sp.trashNothing_backend.dtos.response.TokenResponseDto;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.mapper.AuthMapper;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;
import de.sp.trashNothing_backend.services.AuthentificationService;
import de.sp.trashNothing_backend.services.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthentificationServiceImpl implements AuthentificationService {
    private final BenutzerRepository benutzerRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    public AuthentificationServiceImpl(BenutzerRepository benutzerRepository, PasswordEncoder passwordEncoder, TokenService tokenService, AuthMapper authMapper) {
        this.benutzerRepository = benutzerRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public AuthResponseDto signUp(AuthRequestDto dto) {
        Benutzer benutzer = AuthMapper.toEntity(dto);
        benutzer.setPassword(passwordEncoder.encode(benutzer.getPassword()));
        benutzer = benutzerRepository.save(benutzer);
        return AuthMapper.toResponseDto(benutzer);
    }

    public TokenResponseDto token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);

        Benutzer benutzer = benutzerRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));
        return new TokenResponseDto(token, benutzer.getId());
    }
}