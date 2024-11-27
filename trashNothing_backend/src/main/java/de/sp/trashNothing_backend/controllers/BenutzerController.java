package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.request.GetAllSoldProductsRequestDto;
import de.sp.trashNothing_backend.dtos.request.ProduktVerkauftRequestDto;
import de.sp.trashNothing_backend.dtos.response.GetAllSoldProductsResponseDto;
import de.sp.trashNothing_backend.dtos.response.ProduktEinkaufenResponseDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktVerkauftResponseDto;
import de.sp.trashNothing_backend.services.BenutzerService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/benutzer")
public class BenutzerController {
    BenutzerService benutzerService;

    public BenutzerController(BenutzerService benutzerService) {
        this.benutzerService = benutzerService;
    }

    @GetMapping("/soldItems/{id}")
    public ResponseEntity<List<ProduktEinkaufenResponseDTO>> getListeVerkaufterProdukte(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(benutzerService.getListeverkaufterProdukte(id));

    }

    @GetMapping("/allSoldItems")
    public ResponseEntity<List<ProduktEinkaufenResponseDTO>> getAllVerkauftSetProdukte() {
        return ResponseEntity.ok(benutzerService.getAllVerkauftSetProdukte());
    }

    @PostMapping("/sellItem")
    public ResponseEntity<ProduktEinkaufenResponseDTO> produktVerkaufen(@Valid @RequestBody ProduktVerkauftRequestDto dto) {
        return ResponseEntity.ok(benutzerService.produktVerkauft(dto));
    }
}
