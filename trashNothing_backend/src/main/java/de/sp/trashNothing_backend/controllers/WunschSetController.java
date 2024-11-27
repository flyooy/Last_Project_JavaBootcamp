package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.request.AddProductToWishlistRequestDTO;
import de.sp.trashNothing_backend.dtos.response.WishlistResponseDTO;
import de.sp.trashNothing_backend.entities.WunschSet;
import de.sp.trashNothing_backend.mapper.WunschSetMapper;
import de.sp.trashNothing_backend.services.WunschSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
public class WunschSetController {

    @Autowired
    private WunschSetService wunschSetService;

    // Endpoint to get all wishlist items for all users (already present in your code)
    @GetMapping("/AddToWishlist")
    public ResponseEntity<List<WishlistResponseDTO>> getAllWunschSet() {
        List<WunschSet> wunschSets = wunschSetService.getAllWunschSet();
        List<WishlistResponseDTO> response = wunschSets.stream()
                .map(WunschSetMapper::toWishlistResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint to get wishlist items for a specific user
    @GetMapping("/AddToWishlist/user/{benutzerId}")
    public ResponseEntity<List<WishlistResponseDTO>> getWunschSetByUser(@PathVariable Long benutzerId) {
        List<WunschSet> wunschSets = wunschSetService.getWunschSetsByBenutzerId(benutzerId);
        List<WishlistResponseDTO> response = wunschSets.stream()
                .map(WunschSetMapper::toWishlistResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // New endpoint for adding a product to the wishlist
    @PostMapping("/addToWishlist/{productId}")
    public ResponseEntity<WishlistResponseDTO> addToWishlist(@PathVariable Long productId, @RequestBody AddProductToWishlistRequestDTO request) {
        WunschSet createdWunschSet = wunschSetService.createWunschSet(productId, request);
        WishlistResponseDTO response = WunschSetMapper.toWishlistResponse(createdWunschSet);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/removeFromWishlist/{productId}")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Long productId, @RequestHeader("benutzerId") Long benutzerId) {
        try {
            wunschSetService.removeFromWishlist(productId, benutzerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/removeWishlist/{id}")
    public ResponseEntity<Void> deleteWunschSet(@PathVariable Long id) {
        try {
            wunschSetService.deleteWunschSet(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    }