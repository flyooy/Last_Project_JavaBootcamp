package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.request.AddProductToWishlistRequestDTO;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.entities.WunschSet_Produkt;
import de.sp.trashNothing_backend.entities.WunschSet;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;
import de.sp.trashNothing_backend.repositories.ProduktRepository;
import de.sp.trashNothing_backend.repositories.WunschSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WunschSetService {
    @Autowired
    WunschSetRepository wunschSetRepository;
    @Autowired
    BenutzerRepository benutzerRepository;
    @Autowired
    ProduktRepository produktRepository;

    public WunschSet createWunschSet(Long productId, AddProductToWishlistRequestDTO request) {
        Benutzer benutzer = benutzerRepository.findById(request.benutzerId())
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));
        Produkt produkt = produktRepository.findById(productId) // Используйте productId напрямую
                .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden"));

        WunschSet wunschSet = new WunschSet();
        wunschSet.setBenutzer(benutzer);
        wunschSet.setWunschSetProdukte(Set.of(new WunschSet_Produkt(wunschSet, produkt)));

        return wunschSetRepository.save(wunschSet);
    }


    public List<WunschSet> getAllWunschSet(){
        return  wunschSetRepository.findAll();
    }

    public void deleteWunschSet(Long id){
        wunschSetRepository.deleteById(id);
    }
    public void removeFromWishlist(Long productId, Long benutzerId) {
        List<WunschSet> wunschSets = wunschSetRepository.findByBenutzerId(benutzerId);
        for (WunschSet wunschSet : wunschSets) {
            Optional<WunschSet_Produkt> productToRemove = wunschSet.getWunschSetProdukte().stream()
                    .filter(wunschSetProdukt -> wunschSetProdukt.getProdukt().getId().equals(productId))
                    .findFirst();

            productToRemove.ifPresent(wunschSet.getWunschSetProdukte()::remove);
            wunschSetRepository.save(wunschSet);
        }
    }

    public List<WunschSet> getWunschSetsByBenutzerId(Long benutzerId) {
        return wunschSetRepository.findByBenutzerId(benutzerId);
    }

    public WunschSet getOrCreateWunschSet(Long benutzerId) {
        Benutzer benutzer = benutzerRepository.findById(benutzerId)
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));

        List<WunschSet> wunschSets = wunschSetRepository.findByBenutzerId(benutzerId);

        // Проверяем, есть ли уже WunschSet для этого пользователя
        if (!wunschSets.isEmpty()) {
            return wunschSets.get(0); // Возвращаем первый найденный WunschSet
        } else {
            // Создаем новый WunschSet, если его еще нет
            WunschSet newWunschSet = new WunschSet();
            newWunschSet.setBenutzer(benutzer);
            return wunschSetRepository.save(newWunschSet); // Сохраняем новый WunschSet
        }
    }
    public void addToWishlist(Long benutzerId, Long produktId) {
        Benutzer benutzer = benutzerRepository.findById(benutzerId)
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));
        Produkt produkt = produktRepository.findById(produktId)
                .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden"));

        // Получаем или создаем новый WunschSet
        WunschSet wunschSet = getOrCreateWunschSet(benutzerId);

        // Проверяем, существует ли уже продукт в списке желаемого
        boolean productExists = wunschSet.getWunschSetProdukte().stream()
                .anyMatch(wunschSetProdukt -> wunschSetProdukt.getProdukt().getId().equals(produktId));

        if (!productExists) {
            wunschSet.getWunschSetProdukte().add(new WunschSet_Produkt(wunschSet, produkt));
            wunschSetRepository.save(wunschSet);
        }
    }

}
