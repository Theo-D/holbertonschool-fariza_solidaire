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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbtn.zafirasolidaire.dto.EventCategoryDto;
import com.hbtn.zafirasolidaire.dto.RequestEventCategoryDto;
import com.hbtn.zafirasolidaire.service.EventCategoryFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("event_categories")
@PreAuthorize("hasRole('ADMIN')")
public class EventCategoryController {

    private final EventCategoryFacade eventcategoryFacade;

    @Autowired
    public EventCategoryController(EventCategoryFacade eventcategoryFacade) {
        this.eventcategoryFacade = eventcategoryFacade;
    }

    // ---------- POST ----------//

    // Save a single eventcategory
    @PostMapping
    public ResponseEntity<Void> saveEventCategory(@RequestBody @Valid RequestEventCategoryDto requestEventCategoryDto) {
        eventcategoryFacade.createEventCategory(requestEventCategoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // // Save multiple eventcategorys
    // @PostMapping("/batch")
    // public ResponseEntity<Void> saveAllEventCategorys(@RequestBody @Valid List<EventCategory> eventcategorys) {
    //     eventcategoryFacade.createAllEventCategorys(eventcategorys);
    //     return ResponseEntity.status(HttpStatus.CREATED).build();
    // }

    // ---------- GET ----------//

    // Get eventcategory by ID
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EventCategoryDto> geteventcategoryById(@PathVariable UUID id) {
        EventCategoryDto eventcategoryDto = eventcategoryFacade.getEventCategoryById(id);
        return ResponseEntity.ok(eventcategoryDto);
    }

    // Check if eventcategory exists by ID
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable UUID id) {
        boolean exists = eventcategoryFacade.existsById(id);
        return ResponseEntity.ok(exists);
    }

    // Get all eventcategorys
    @GetMapping
    public ResponseEntity<Iterable<EventCategoryDto>> getAlleventcategorys() {
        Iterable<EventCategoryDto> eventcategoryDtos = eventcategoryFacade.getAllEventCategories();
        return ResponseEntity.ok(eventcategoryDtos);
    }

    // Count eventcategorys
    @GetMapping("/count")
    public ResponseEntity<Long> counteventcategorys() {
        long count = eventcategoryFacade.countEventCategories();
        return ResponseEntity.ok(count);
    }

    // ---------- DELETE ----------//

    // Delete eventcategory by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventCategoryById(@PathVariable UUID id) {
        eventcategoryFacade.deleteEventCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    // // Delete a single eventcategory (full object)
    // @DeleteMapping
    // public ResponseEntity<Void> deleteEventCategory(@RequestBody EventCategory eventcategory) {
    //     eventcategoryFacade.deleteEventCategory(eventcategory);
    //     return ResponseEntity.noContent().build();
    // }

    // // Delete all eventcategorys
    // @DeleteMapping("/all")
    // public ResponseEntity<Void> deleteAllEventCategorys() {
    //     eventcategoryFacade.deleteAllEventCategorys();
    //     return ResponseEntity.noContent().build();
    // }

    // // Delete specific eventcategorys
    // @DeleteMapping("/batch")
    // public void deleteAllEventCategorysFromList(@RequestBody List<EventCategory> eventcategorys) {
    //     eventcategoryFacade.deleteAllPArtnersFromList(eventcategorys);
    // }
}
