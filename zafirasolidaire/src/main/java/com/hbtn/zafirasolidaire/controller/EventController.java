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

import com.hbtn.zafirasolidaire.dto.EventDto;
import com.hbtn.zafirasolidaire.dto.RequestEventDto;
import com.hbtn.zafirasolidaire.dto.RequestPhotoDto;
import com.hbtn.zafirasolidaire.service.EventFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("events")
@PreAuthorize("hasRole('ADMIN')")
public class EventController {
    private final EventFacade eventFacade;

    @Autowired
    public EventController(EventFacade eventFacade) {
        this.eventFacade = eventFacade;
    }

    // ---------- POST ----------//

    // Save a single event
    @PostMapping
    public ResponseEntity<Void> saveEvent(@RequestBody @Valid RequestEventDto requestEventDto) {
        eventFacade.createEvent(requestEventDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{eventId}/photo")
    public ResponseEntity<Void> saveEvent(@RequestBody @Valid RequestPhotoDto photoDto, @PathVariable UUID eventId) {
        eventFacade.addPhoto(photoDto, eventId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // // Save multiple events
    // @PostMapping("/batch")
    // public ResponseEntity<Void> saveAllEvents(@RequestBody @Valid List<EventDto> eventDtos) {
    //     eventFacade.createAllEvents(eventDtos);
    //     return ResponseEntity.status(HttpStatus.CREATED).build();
    // }

    // ---------- GET ----------//

    // Get events by list of IDs
    // POST because body needed in request to get Iterable<eventDto>
    // @PostMapping("/batch/ids")
    // public ResponseEntity<Iterable<eventDto>> getAlleventsById(@RequestBody List<UUID> ids) {
    //     Iterable<eventDto> events = eventFacade.getAlleventsById(ids);
    //     return ResponseEntity.ok(events);
    // }

    // Get event by ID
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EventDto> getEventById(@PathVariable UUID id) {
        EventDto eventDto = eventFacade.getEventById(id);
        return ResponseEntity.ok(eventDto);
    }

    // Check if event exists by ID
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable UUID id) {
        boolean exists = eventFacade.existsById(id);
        return ResponseEntity.ok(exists);
    }

    // Get all events
    @GetMapping
    public ResponseEntity<Iterable<EventDto>> getAllEvents() {
        Iterable<EventDto> events = eventFacade.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // Count events
    @GetMapping("/count")
    public ResponseEntity<Long> countEvents() {
        long count = eventFacade.countEvents();
        return ResponseEntity.ok(count);
    }

    // ---------- DELETE ----------//

    // Delete event by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable UUID id) {
        eventFacade.deleteEventById(id);
        return ResponseEntity.noContent().build();
    }

    // // Delete a single event (full object)
    // @DeleteMapping
    // public ResponseEntity<Void> deleteEvent(@RequestBody Event event) {
    //     eventFacade.deleteEvent(event);
    //     return ResponseEntity.noContent().build();
    // }

    // // Delete all events
    // @DeleteMapping("/all")
    // public ResponseEntity<Void> deleteAllEvents() {
    //     eventFacade.deleteAllEvents();
    //     return ResponseEntity.noContent().build();
    // }

    // // Delete specific events
    // @DeleteMapping("/batch")
    // public ResponseEntity<Void> deleteAllEventsFromlist(@RequestBody List<Event> events) {
    //     eventFacade.deleteAllEvents(events);
    //     return ResponseEntity.noContent().build();
    // }
}
