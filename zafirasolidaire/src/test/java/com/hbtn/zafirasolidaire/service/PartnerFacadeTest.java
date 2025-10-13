package com.hbtn.zafirasolidaire.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbtn.zafirasolidaire.model.Partner;
import com.hbtn.zafirasolidaire.repository.PartnerRepository;

@ExtendWith(MockitoExtension.class)
public class PartnerFacadeTest {

    @Mock
    private PartnerRepository partnerRepository;

    @InjectMocks
    private PartnerFacade partnerFacade;

    private Partner partner;
    private UUID partnerId;

    @BeforeEach
    void setUp() {
        partnerId = UUID.randomUUID();
        partner = new Partner().setName("Test Partner");
    }

    //---------- createPartner ----------//

    @Test
    void createPartner_shouldSavePartner() {
        partnerFacade.createPartner(partner);
        verify(partnerRepository).save(partner);
    }

    @Test
    void createPartner_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> partnerFacade.createPartner(null));
    }

    //---------- createAllPartners ----------//

    @Test
    void createAllPartners_shouldSaveAllPartners() {
        List<Partner> partners = List.of(partner);
        partnerFacade.createAllPartners(partners);
        verify(partnerRepository).saveAll(partners);
    }

    @Test
    void createAllPartners_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> partnerFacade.createAllPartners(null));
    }

    @Test
    void createAllPartners_shouldThrowException_whenEmpty() {
        assertThrows(IllegalArgumentException.class, () -> partnerFacade.createAllPartners(Collections.emptyList()));
    }

    //---------- getPartnerById ----------//

    @Test
    void getPartnerById_shouldReturnPartner() {
        when(partnerRepository.findById(partnerId)).thenReturn(Optional.of(partner));
        Partner result = partnerFacade.getPartnerById(partnerId);
        assertEquals(partner, result);
    }

    @Test
    void getPartnerById_shouldThrowException_whenIdNull() {
        assertThrows(IllegalArgumentException.class, () -> partnerFacade.getPartnerById(null));
    }

    @Test
    void getPartnerById_shouldThrowException_whenNotFound() {
        when(partnerRepository.findById(partnerId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> partnerFacade.getPartnerById(partnerId));
    }

    //---------- getAllPartners ----------//

    @Test
    void getAllPartners_shouldReturnAllPartners() {
        List<Partner> partners = List.of(partner);
        when(partnerRepository.findAll()).thenReturn(partners);
        Iterable<Partner> result = partnerFacade.getAllPartners();
        assertEquals(partners, result);
    }

    //---------- existsById ----------//

    @Test
    void existsById_shouldReturnTrueIfExists() {
        when(partnerRepository.existsById(partnerId)).thenReturn(true);
        assertTrue(partnerFacade.existsById(partnerId));
    }

    @Test
    void existsById_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> partnerFacade.existsById(null));
    }

    //---------- getAllPartnersById ----------//

    @Test
    void getAllPartnersById_shouldReturnPartners() {
        List<UUID> ids = List.of(partnerId);
        List<Partner> partners = List.of(partner);
        when(partnerRepository.findAllById(ids)).thenReturn(partners);

        Iterable<Partner> result = partnerFacade.getAllPartnersById(ids);
        assertEquals(partners, result);
    }

    @Test
    void getAllPartnersById_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> partnerFacade.getAllPartnersById(null));
    }

    @Test
    void getAllPartnersById_shouldThrowException_whenEmpty() {
        assertThrows(IllegalArgumentException.class, () -> partnerFacade.getAllPartnersById(Collections.emptyList()));
    }

    //---------- deletePartnerById ----------//

    @Test
    void deletePartnerById_shouldDeletePartner() {
        partnerFacade.deletePartnerById(partnerId);
        verify(partnerRepository).deleteById(partnerId);
    }

    @Test
    void deletePartnerById_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> partnerFacade.deletePartnerById(null));
    }

    //---------- deletePartner ----------//

    @Test
    void deletePartner_shouldDeletePartner() {
        partnerFacade.deletePartner(partner);
        verify(partnerRepository).delete(partner);
    }

    @Test
    void deletePartner_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> partnerFacade.deletePartner(null));
    }

    //---------- deleteAllPartners ----------//

    @Test
    void deleteAllPartners_shouldCallDeleteAll() {
        partnerFacade.deleteAllPartners();
        verify(partnerRepository).deleteAll();
    }

    //---------- deleteAllPartners ----------//

    @Test
    void deleteAllPartners_shouldDeleteAll() {
        List<Partner> partners = List.of(partner);
        partnerFacade.deleteAllPArtnersFromList(partners);
        verify(partnerRepository).deleteAll(partners);
    }

    @Test
    void deleteAllPartners_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> partnerFacade.deleteAllPArtnersFromList(null));
    }

    @Test
    void deleteAllPartners_shouldThrowException_whenEmpty() {
        assertThrows(IllegalArgumentException.class, () -> partnerFacade.deleteAllPArtnersFromList(Collections.emptyList()));
    }

    //---------- countPartners ----------//

    @Test
    void countPartners_shouldReturnCount() {
        when(partnerRepository.count()).thenReturn(5L);
        long count = partnerFacade.countPartners();
        assertEquals(5L, count);
    }
}
