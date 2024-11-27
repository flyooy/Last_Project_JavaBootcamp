package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.request.ProduktRequestDTOWithImage;
import de.sp.trashNothing_backend.dtos.response.ProduktResponseDTO;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.mapper.ProduktMapper;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;
import de.sp.trashNothing_backend.services.ProduktServiceWithImage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product/withImage")
public class ProduktControllerWithImage {

    private final ProduktServiceWithImage produktService;
    private final BenutzerRepository benutzerRepository;

    @Autowired
    public ProduktControllerWithImage(ProduktServiceWithImage produktService, BenutzerRepository benutzerRepository) {
        this.produktService = produktService;
        this.benutzerRepository = benutzerRepository;
    }

    @PostMapping
    public ResponseEntity<ProduktResponseDTO> createProdukt(@Valid @ModelAttribute ProduktRequestDTOWithImage request) {
        Benutzer benutzer = benutzerRepository.findById(request.benutzerId())
                .orElseThrow(() -> new IllegalArgumentException("User noch nicht gefunden"));

        //create Produkt
        Produkt createdProdukt = produktService.createProdukt(request, benutzer);
        ProduktResponseDTO response = ProduktMapper.toProduktResponse(createdProdukt);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}