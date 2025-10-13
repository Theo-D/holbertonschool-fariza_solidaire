package com.hbtn.zafirasolidaire.service;

import org.springframework.stereotype.Service;

import static com.hbtn.zafirasolidaire.config.ClothingInitializer.CLOTHING_UUID;

import com.hbtn.zafirasolidaire.model.Clothing;
import com.hbtn.zafirasolidaire.repository.ClothingRepository;

@Service
public class ClothingFacade {
    private final ClothingRepository clothingRepository;

    public ClothingFacade(ClothingRepository clothingRepository){
        this.clothingRepository = clothingRepository;
    }

    // ---------- Repository Services ---------- //

    public Clothing getClothing() {
        Clothing clothing = clothingRepository.findById(CLOTHING_UUID)
                                              .orElseThrow(() -> new IllegalArgumentException("Clothing not found"));

        return clothing;
    }

    public void updateClothingWeight(int weight) {
        Clothing clothing = getClothing();

        clothing.setWeight(weight);

        clothingRepository.save(clothing);
    }

    public void incrementClothingWeight(int addedWeight) {
        Clothing clothing = getClothing();
        int weight = clothing.getWeight();

        clothing.setWeight(weight + addedWeight);

        clothingRepository.save(clothing);
    }
}
