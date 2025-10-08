package com.hbtn.zafirasolidaire.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.model.Partner;
import com.hbtn.zafirasolidaire.repository.PartnerRepository;

@Service
public class PartnerFacade {
    private final PartnerRepository partnerRepository;

    @Autowired
    public PartnerFacade(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public void createPartner(Partner partner) {
        if (partner == null) {
            throw new IllegalArgumentException("Partner cannot be null.");
        }
        partnerRepository.save(partner);
    }

    public void createAllPartners(Iterable<Partner> partners) {
        if (partners == null || !partners.iterator().hasNext()) {
            throw new IllegalArgumentException("Partner list cannot be null or empty.");
        }
        partnerRepository.saveAll(partners);
    }

    public Partner getPartnerById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Partner cannot be null.");
        }

        Partner partner = partnerRepository.findById(id)
                                           .orElseThrow(() -> new IllegalArgumentException("Partner not found"));
        return partner;
    }

    public Iterable<Partner> getAllPartners() {
        return partnerRepository.findAll();
    }

    public boolean existsById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Partner ID cannot be null.");
        }
        return partnerRepository.existsById(id);
    }

    public Iterable<Partner> getAllPartnersById(Iterable<UUID> ids) {
        if (ids == null || !ids.iterator().hasNext()) {
            throw new IllegalArgumentException("Partner id list cannot be null or empty.");
        }

        return partnerRepository.findAllById(ids);
    }

    public void deletePartnerById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Partner ID cannot be null.");
        }
        partnerRepository.deleteById(id);
    }

    public void deletePartner(Partner partner) {
        if (partner == null) {
            throw new IllegalArgumentException("Partner cannot be null.");
        }
        partnerRepository.delete(partner);
    }

    public void deleteAllPartners() {
        partnerRepository.deleteAll();
    }

    public void deleteAllPArtnersFromList(Iterable<Partner> partners) {
        if (partners == null || !partners.iterator().hasNext()) {
            throw new IllegalArgumentException("Partner list cannot be null or empty.");
        }
        partnerRepository.deleteAll(partners);
    }

    public long countPartners() {
        return partnerRepository.count();
    }
}
