package com.hbtn.zafirasolidaire.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.dto.PartnerDto;
import com.hbtn.zafirasolidaire.dto.RequestPartnerDto;
import com.hbtn.zafirasolidaire.mapper.PartnerMapper;
import com.hbtn.zafirasolidaire.model.Partner;
import com.hbtn.zafirasolidaire.repository.PartnerRepository;

@Service
public class PartnerFacade {
    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    @Autowired
    public PartnerFacade(PartnerRepository partnerRepository, PartnerMapper partnerMapper) {
        this.partnerRepository = partnerRepository;
        this.partnerMapper = partnerMapper;
    }

    //---------- Repository Services ----------//
    public void createPartner(RequestPartnerDto requestPartnerDto) {
        if (requestPartnerDto == null) {
            throw new IllegalArgumentException("Partner cannot be null.");
        }

        Partner partner = partnerMapper.requestDtoToPartner(requestPartnerDto);

        partnerRepository.save(partner);
    }

    public void createAllPartners(Iterable<RequestPartnerDto> requestPartnerDtos) {
        if (requestPartnerDtos == null || !requestPartnerDtos.iterator().hasNext()) {
            throw new IllegalArgumentException("Partner list cannot be null or empty.");
        }

        List<Partner> partners = new ArrayList<>();

        for (RequestPartnerDto requestPartnerDto : requestPartnerDtos) {
            Partner partner = partnerMapper.requestDtoToPartner(requestPartnerDto);
            partners.add(partner);
        }
        partnerRepository.saveAll(partners);
    }

    public PartnerDto getPartnerById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Partner ID cannot be null.");
        }

        Partner foundPartner = partnerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Partner not found"));

        return partnerMapper.partnerToDto(foundPartner);
    }

    public boolean existsById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Partner ID cannot be null.");
        }
        return partnerRepository.existsById(id);
    }

    public Iterable<PartnerDto> getAllPartners() {
        return mapToDto(partnerRepository.findAll());
    }

    public Iterable<PartnerDto> getAllPartnersById(Iterable<UUID> ids) {
        if (ids == null || !ids.iterator().hasNext()) {
            throw new IllegalArgumentException("ID list cannot be null or empty.");
        }

        return mapToDto(partnerRepository.findAllById(ids));
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

    public void deleteAllPartners(Iterable<Partner> partners) {
        if (partners == null || !partners.iterator().hasNext()) {
            throw new IllegalArgumentException("Partner list cannot be null or empty.");
        }
        partnerRepository.deleteAll(partners);
    }

    public long countPartners() {
        return partnerRepository.count();
    }

    //--------- Helper methods ---------//

    private Iterable<PartnerDto> mapToDto(Iterable<Partner> partners) {
        return StreamSupport.stream(partners.spliterator(), false)
            .map(partnerMapper::partnerToDto)
            .collect(Collectors.toList());
    }

    public Partner mapDtoToPartner(RequestPartnerDto dto) {
        return partnerMapper.requestDtoToPartner(dto);
    }

    public void savePartner(Partner partner) {
        partnerRepository.save(partner);
    }
}
