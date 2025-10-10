package com.hbtn.zafirasolidaire.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.google.common.collect.Iterables;
import com.hbtn.zafirasolidaire.model.Clothing;
import com.hbtn.zafirasolidaire.repository.ClothingRepository;

@Component
public class ClothingInitializer implements CommandLineRunner {

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
