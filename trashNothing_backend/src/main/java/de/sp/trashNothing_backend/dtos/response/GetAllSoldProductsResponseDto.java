package de.sp.trashNothing_backend.dtos.response;

import de.sp.trashNothing_backend.entities.Produkt;

import java.util.Set;

public record GetAllSoldProductsResponseDto(
        Set<Produkt> ListeVerkaufterProdukte
) {
}
