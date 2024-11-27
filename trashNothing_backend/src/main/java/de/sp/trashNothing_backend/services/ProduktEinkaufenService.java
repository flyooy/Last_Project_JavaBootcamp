package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.request.ProduktEinkaufenRequestDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktEinkaufenResponseDTO;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.GekauftList;
import de.sp.trashNothing_backend.entities.GekauftSet_Produkt;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.entities.enumClass.Kategorie;
import de.sp.trashNothing_backend.mapper.ProduktMapper;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;
import de.sp.trashNothing_backend.repositories.GekauftListRepository;
import de.sp.trashNothing_backend.repositories.GekauftSet_ProduktRepository;
import de.sp.trashNothing_backend.repositories.ProduktRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduktEinkaufenService {

    private final ProduktRepository produktRepository;
    private final BenutzerRepository benutzerRepository;
    private final GekauftSet_ProduktRepository gekauftSet_produktRepository;
    private final GekauftListRepository gekauftListRepository;


    @Autowired
    public ProduktEinkaufenService(ProduktRepository produktRepository, BenutzerRepository benutzerRepository, GekauftSet_ProduktRepository gekauftSet_produktRepository,
                                   GekauftListRepository gekauftListRepository) {
        this.produktRepository = produktRepository;
        this.benutzerRepository = benutzerRepository;
        this.gekauftSet_produktRepository = gekauftSet_produktRepository;
        this.gekauftListRepository = gekauftListRepository;
    }

    //morgen weiter Deng
    @Transactional
    public ProduktEinkaufenResponseDTO kaufenProdukt(ProduktEinkaufenRequestDTO requestDTO) {
        Benutzer benutzer = benutzerRepository.findById(requestDTO.benutzerId())
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));

        Produkt produkt = produktRepository.findById(requestDTO.produktId())
                .orElseThrow(() -> new RuntimeException("Produkt nicht gefunden"));

        // get and create  Benutzer of  GekauftList object
        GekauftList gekauftList;
        if (benutzer.getGekauftSet().isEmpty()) {
            gekauftList = new GekauftList();
            gekauftList.setBenutzer(benutzer);
            gekauftList = gekauftListRepository.save(gekauftList);
            benutzer.getGekauftSet().add(gekauftList);
        } else {
            //  Benutzer hat 1 GekauftList
            gekauftList = benutzer.getGekauftSet().get(0);
        }

        // create GekauftSet_Produkt kontakt with GekauftList and Produkt
        GekauftSet_Produkt gekauftSetProdukt = new GekauftSet_Produkt();
        gekauftSetProdukt.setGekauftSet(gekauftList);
        gekauftSetProdukt.setProdukt(produkt);
        gekauftSet_produktRepository.save(gekauftSetProdukt);

        return ProduktMapper.toProduktEinkaufenResponseDTO(produkt);
    }

    public ProduktEinkaufenResponseDTO getProduktDetailById(Long id) {
        Produkt produkt = produktRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produkt mit ID " + id + " wurde nicht gefunden"));
        return ProduktMapper.toProduktEinkaufenResponseDTO(produkt);
    }


    public List<ProduktEinkaufenResponseDTO> getAllProdukte() {
        return produktRepository.findAll().stream()
                .map(ProduktMapper::toProduktEinkaufenResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ProduktEinkaufenResponseDTO> getGekaufteProdukteByBenutzerId(Long benutzerId) {
        Benutzer benutzer = benutzerRepository.findById(benutzerId)
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));

        // get GekauftList von Benutzer
        if (benutzer.getGekauftSet().isEmpty()) {
            return List.of(); // if not record return empty list
        }

        GekauftList gekauftList = benutzer.getGekauftSet().get(0);

        // get Produkt from GekauftList
        return gekauftList.getGekauftSetProdukte().stream()
                .map(GekauftSet_Produkt::getProdukt)
                .map(ProduktMapper::toProduktEinkaufenResponseDTO)
                .collect(Collectors.toList());
    }


    public List<ProduktEinkaufenResponseDTO> searchProdukteByTitle(String title) {
        return produktRepository.findByTitelContainingIgnoreCase(title).stream()
                .map(ProduktMapper::toProduktEinkaufenResponseDTO)
                .collect(Collectors.toList());
    }


    public List<ProduktEinkaufenResponseDTO> searchProdukteByKategorie(Kategorie kategorie) {
        return produktRepository.findByKategorie(kategorie).stream()
                .map(ProduktMapper::toProduktEinkaufenResponseDTO)
                .collect(Collectors.toList());
    }


    public List<ProduktEinkaufenResponseDTO> searchProdukteByTitleAndKategorie(String title, Kategorie kategorie) {
        return produktRepository.searchByTitleAndKategorie(title, kategorie).stream()
                .map(ProduktMapper::toProduktEinkaufenResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ProduktEinkaufenResponseDTO> searchProdukteByTitleOderKategorie(String title, Kategorie kategorie) {
        List<Produkt> produkte;
        if (title != null && kategorie != null) {
            produkte = produktRepository.searchByTitleOderKategorie(title, kategorie);
        } else if (title != null) {
            produkte = produktRepository.findByTitelContainingIgnoreCase(title);
        } else if (kategorie != null) {
            produkte = produktRepository.findByKategorie(kategorie);
        } else {
            produkte = produktRepository.findAll();
        }

        return produkte.stream()
                .map(ProduktMapper::toProduktEinkaufenResponseDTO)
                .collect(Collectors.toList());
    }
}