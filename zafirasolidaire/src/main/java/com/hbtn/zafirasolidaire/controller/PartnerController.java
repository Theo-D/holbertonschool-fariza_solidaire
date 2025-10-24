package com.hbtn.zafirasolidaire.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbtn.zafirasolidaire.dto.PartnerDto;
import com.hbtn.zafirasolidaire.dto.RequestPartnerDto;
import com.hbtn.zafirasolidaire.service.PartnerFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("partners")
public class PartnerController {
    private final PartnerFacade partnerFacade;

    @Autowired
    public PartnerController(PartnerFacade partnerFacade) {
        this.partnerFacade = partnerFacade;
    }

    // ---------- POST ----------//


    // Save a single partner
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> savePartner(@RequestBody @Valid RequestPartnerDto requestPartnerDto) {
        partnerFacade.createPartner(requestPartnerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePartner(@PathVariable UUID id, @RequestBody RequestPartnerDto requestPartnerDto) {
        if (!partnerFacade.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        partnerFacade.updatePartner(id, requestPartnerDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // // Save multiple partners
    // @PostMapping("/batch")
    // public ResponseEntity<Void> saveAllPartners(@RequestBody @Valid List<Partner> partners) {
    //     partnerFacade.createAllPartners(partners);
    //     return ResponseEntity.status(HttpStatus.CREATED).build();
    // }

    // ---------- GET ----------//

    // Get partner by ID
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PartnerDto> getpartnerById(@PathVariable UUID id) {
        PartnerDto partnerDto = partnerFacade.getPartnerById(id);
        return ResponseEntity.ok(partnerDto);
    }

    // Check if partner exists by ID
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable UUID id) {
        boolean exists = partnerFacade.existsById(id);
        return ResponseEntity.ok(exists);
    }

    // Get all partners
    @GetMapping
    public ResponseEntity<Iterable<PartnerDto>> getAllpartners() {
        Iterable<PartnerDto> partnerDtos = partnerFacade.getAllPartners();
        return ResponseEntity.ok(partnerDtos);
    }

    // Count partners
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/count")
    public ResponseEntity<Long> countpartners() {
        long count = partnerFacade.countPartners();
        return ResponseEntity.ok(count);
    }

    // ---------- DELETE ----------//

    // Delete partner by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartnerById(@PathVariable UUID id) {
        partnerFacade.deletePartnerById(id);
        return ResponseEntity.noContent().build();
    }

    // // Delete a single partner (full object)
    // @DeleteMapping
    // public ResponseEntity<Void> deletePartner(@RequestBody Partner partner) {
    //     partnerFacade.deletePartner(partner);
    //     return ResponseEntity.noContent().build();
    // }

    // // Delete all partners
    // @DeleteMapping("/all")
    // public ResponseEntity<Void> deleteAllPartners() {
    //     partnerFacade.deleteAllPartners();
    //     return ResponseEntity.noContent().build();
    // }

    // // Delete specific partners
    // @DeleteMapping("/batch")
    // public void deleteAllPartnersFromList(@RequestBody List<Partner> partners) {
    //     partnerFacade.deleteAllPArtnersFromList(partners);
    // }
}
