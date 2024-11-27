package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.response.BenutzerDetailResponseDTO;
import de.sp.trashNothing_backend.services.BenutzerDetailService;
import de.sp.trashNothing_backend.services.BenutzerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/benutzerDetail")
public class BenutzerDetailController {
    private final BenutzerDetailService benutzerDetailService;

    public BenutzerDetailController(BenutzerDetailService benutzerDetailService) {
        this.benutzerDetailService = benutzerDetailService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BenutzerDetailResponseDTO> getBenutzerById(@PathVariable Long id) {
        BenutzerDetailResponseDTO benutzerDetail = benutzerDetailService.getBenutzerById(id);
        return ResponseEntity.ok(benutzerDetail);
    }
}