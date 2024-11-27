package de.sp.trashNothing_backend.controllers;


import de.sp.trashNothing_backend.dtos.request.ProduktEinkaufenRequestDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktEinkaufenResponseDTO;
import de.sp.trashNothing_backend.entities.enumClass.Kategorie;
import de.sp.trashNothing_backend.services.ProduktEinkaufenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import de.sp.trashNothing_backend.dtos.request.ProduktEinkaufenRequestDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktEinkaufenResponseDTO;
import de.sp.trashNothing_backend.entities.enumClass.Kategorie;
import de.sp.trashNothing_backend.services.ProduktEinkaufenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produkte")
public class ProduktEinkaufenController {

    private final ProduktEinkaufenService produktEinkaufenService;

    @Autowired
    public ProduktEinkaufenController(ProduktEinkaufenService produktEinkaufenService) {
        this.produktEinkaufenService = produktEinkaufenService;
    }

    @PostMapping("addToShoppinglist")
    public ResponseEntity<ProduktEinkaufenResponseDTO> addProduktToGekauftList(
            @RequestBody ProduktEinkaufenRequestDTO requestDTO) {
        ProduktEinkaufenResponseDTO responseDTO = produktEinkaufenService.kaufenProdukt(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProduktEinkaufenResponseDTO>> getAllProdukte() {
        List<ProduktEinkaufenResponseDTO> responseDTOs = produktEinkaufenService.getAllProdukte();
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @GetMapping("/gekauft/{benutzerId}")
    public ResponseEntity<List<ProduktEinkaufenResponseDTO>> getGekaufteProdukteByBenutzerId(
            @PathVariable Long benutzerId) {
        List<ProduktEinkaufenResponseDTO> responseDTOs = produktEinkaufenService.getGekaufteProdukteByBenutzerId(benutzerId);
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduktEinkaufenResponseDTO> getProduktDetailByID(@PathVariable Long id) {
        try {
            ProduktEinkaufenResponseDTO produktDetail = produktEinkaufenService.getProduktDetailById(id);
            return ResponseEntity.ok(produktDetail);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/searchByTitle")
    public List<ProduktEinkaufenResponseDTO> searchByTitle(@RequestParam String title) {
        return produktEinkaufenService.searchProdukteByTitle(title);
    }

    @GetMapping("/searchByKategorie")
    public List<ProduktEinkaufenResponseDTO> searchByKategorie(@RequestParam Kategorie kategorie) {
        return produktEinkaufenService.searchProdukteByKategorie(kategorie);
    }

    @GetMapping("/search")
    public List<ProduktEinkaufenResponseDTO> searchByTitleOderKategorie(@RequestParam(required = false) String title,
                                                                        @RequestParam(required = false) Kategorie kategorie) {
        return produktEinkaufenService.searchProdukteByTitleOderKategorie(title, kategorie);
    }


}