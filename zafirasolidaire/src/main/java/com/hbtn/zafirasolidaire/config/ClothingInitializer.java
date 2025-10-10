package com.hbtn.zafirasolidaire.config;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.google.common.collect.Iterables;
import com.hbtn.zafirasolidaire.model.Clothing;
import com.hbtn.zafirasolidaire.repository.ClothingRepository;

@Component
public class ClothingInitializer implements CommandLineRunner {

    public static final UUID CLOTHING_UUID = UUID.fromString("7fdc74b2-c8c1-41ba-89d2-efa772d4b627");

    @Autowired
    private ClothingRepository clothingRepository;

    @Override
    public void run(String... args) {
        Iterable<Clothing> foundClothing = clothingRepository.findAll();

        if (Iterables.size(foundClothing) != 1) {
            Clothing clothing = new Clothing();
            clothing.setWeight(0);
            clothingRepository.save(clothing);
        }
    }
}
