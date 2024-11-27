package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.request.ProduktRequestDTO;
import de.sp.trashNothing_backend.dtos.request.ProduktRequestDTOWithImage;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;
import de.sp.trashNothing_backend.repositories.ProduktRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class ProduktServiceWithImage {

    private final ProduktRepository produktRepository;
    private final BenutzerRepository benutzerRepository;
    private final ImageUploadService imageUploadService;

    public ProduktServiceWithImage(ProduktRepository produktRepository, BenutzerRepository benutzerRepository,
                                   ImageUploadService imageUploadService) {
        this.produktRepository = produktRepository;
        this.benutzerRepository = benutzerRepository;
        this.imageUploadService = imageUploadService;
    }

    public Produkt createProdukt(ProduktRequestDTOWithImage request, Benutzer benutzer) {
        String imageUrl = null;
        String deleteUrl = null;

        if (request.imgFile() != null) {
            Map<String, String> imgUrls = imageUploadService.uploadImage(request.imgFile());
            imageUrl = imgUrls.get("imgUrl");
            deleteUrl = imgUrls.get("deleteUrl");
        }

        Produkt produkt = new Produkt(
                request.titel(),
                request.beschreibung(),
                request.anzahl(),
                request.preis(),
                request.zustand(),
                request.marke(),
                request.lieferung(),
                imageUrl,
                deleteUrl,
                request.kategorie(),
                benutzer
        );

        return produktRepository.save(produkt);
    }


}