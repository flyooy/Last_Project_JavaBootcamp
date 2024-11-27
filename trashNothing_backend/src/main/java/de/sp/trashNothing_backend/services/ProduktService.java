package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.request.ProduktRequestDTO;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.mapper.ProduktMapper;
import de.sp.trashNothing_backend.repositories.ProduktRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduktService {
    @Autowired
    ProduktRepository produktRepository;


    public Produkt createProdukt(@Valid ProduktRequestDTO produktRequestDTO, Benutzer benutzer){
        Produkt produkt = ProduktMapper.toProdukt(produktRequestDTO, benutzer);
        return produktRepository.save(produkt);
    }

    public Produkt updateProdukt(@Valid Long id, ProduktRequestDTO produktRequestDTO) {

        Produkt existingProdukt = produktRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produkt mit ID " + id + " wurde nicht gefunden"));


        existingProdukt.setTitel(produktRequestDTO.titel());
        existingProdukt.setBeschreibung(produktRequestDTO.beschreibung());
        existingProdukt.setAnzahl(produktRequestDTO.anzahl());
        existingProdukt.setPreis(produktRequestDTO.preis());
        existingProdukt.setZustand(produktRequestDTO.zustand());
        existingProdukt.setMarke(produktRequestDTO.marke());
        existingProdukt.setLieferung(produktRequestDTO.lieferung());
        existingProdukt.setImgUrl(produktRequestDTO.imgUrl());
        existingProdukt.setDeleteUrl(produktRequestDTO.deleteUrl());
        existingProdukt.setKategorie(produktRequestDTO.kategorie());


        return produktRepository.save(existingProdukt);
    }
}
