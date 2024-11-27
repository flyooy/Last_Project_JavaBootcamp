package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.response.BenutzerDetailResponseDTO;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.mapper.BenutzerDetailMapper;
import de.sp.trashNothing_backend.repositories.BenutzerDetailRepository;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenutzerDetailService {

    private final BenutzerDetailRepository benutzerDetailRepository;

    @Autowired
    public BenutzerDetailService(BenutzerDetailRepository benutzerDetailRepository) {
        this.benutzerDetailRepository = benutzerDetailRepository;
    }

    public BenutzerDetailResponseDTO getBenutzerById(Long id) {
        Benutzer benutzer = benutzerDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Benutzer mit ID " + id + " nicht gefunden"));
        return BenutzerDetailMapper.toDto(benutzer);
    }
}