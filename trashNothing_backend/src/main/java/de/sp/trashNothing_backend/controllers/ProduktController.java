package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.request.ProduktRequestDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktResponseDTO;
import de.sp.trashNothing_backend.dtos.response.WishlistResponseDTO;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.entities.enumClass.Kategorie;
import de.sp.trashNothing_backend.entities.enumClass.Zustand;
import de.sp.trashNothing_backend.mapper.ProduktMapper;
import de.sp.trashNothing_backend.mapper.WunschSetMapper;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;
import de.sp.trashNothing_backend.services.ProduktService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static de.sp.trashNothing_backend.mapper.ProduktMapper.toProduktResponse;

@RestController
@RequestMapping("/api/v1/product")
public class ProduktController {
    @Autowired
    ProduktService produktService;
    @Autowired
    BenutzerRepository benutzerRepository;

    @PostMapping
    public ResponseEntity<ProduktResponseDTO> createProdukt(@Valid @RequestBody ProduktRequestDTO request) {
        Benutzer benutzer = benutzerRepository.findById(request.benutzerId())
                .orElseThrow(() -> new IllegalArgumentException("User noch nicht gefunden"));
        Produkt createdProdukt = produktService.createProdukt(request, benutzer);
        ProduktResponseDTO response = toProduktResponse(createdProdukt);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProduktResponseDTO> updateProdukt(
            @PathVariable Long id,
            @RequestParam("titel") String titel,
            @RequestParam("beschreibung") String beschreibung,
            @RequestParam("anzahl") int anzahl,
            @RequestParam("preis") BigDecimal preis,
            @RequestParam("zustand") Zustand zustand,
            @RequestParam("marke") String marke,
            @RequestParam(value = "lieferung", required = false) boolean lieferung,
            @RequestParam(value = "imgUrl", required = false) String imgUrl,
            @RequestParam(value = "deleteUrl", required = false) String deleteUrl,
            @RequestParam("kategorie") Kategorie kategorie,
            @RequestParam("benutzerId") Long benutzerId) {

        ProduktRequestDTO produktRequestDTO = new ProduktRequestDTO(titel, beschreibung, anzahl, preis, zustand, marke, lieferung, imgUrl, deleteUrl, kategorie, benutzerId);

        try {
            Produkt updatedProdukt = produktService.updateProdukt(id, produktRequestDTO);
            ProduktResponseDTO responseDTO = ProduktMapper.toProduktResponse(updatedProdukt);
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
