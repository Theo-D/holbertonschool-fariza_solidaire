package com.hbtn.zafirasolidaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbtn.zafirasolidaire.model.Clothing;
import com.hbtn.zafirasolidaire.service.ClothingFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("clothing")
public class ClothingController {

    private final ClothingFacade clothingFacade;

    @Autowired
    public ClothingController(ClothingFacade clothingFacade) {
        this.clothingFacade = clothingFacade;
    }
    // ---------- POST ----------//

    // Add clothing weight
    @PostMapping("/add")
    public ResponseEntity<Void> incrementClothingWeight(@RequestBody @Valid int addedWeight) {
        clothingFacade.incrementClothingWeight(addedWeight);;
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // change total clothing weight
    @PostMapping("/save")
    public ResponseEntity<Void> saveClothingWeight(@RequestBody @Valid int totalWeight) {
        clothingFacade.updateClothingWeight(totalWeight);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // ---------- GET ----------//
    // Get clothing
    @GetMapping(produces = "application/json")
    public ResponseEntity<Clothing> getEventById() {
        Clothing clothing = clothingFacade.getClothing();

        return ResponseEntity.ok(clothing);
    }
}
