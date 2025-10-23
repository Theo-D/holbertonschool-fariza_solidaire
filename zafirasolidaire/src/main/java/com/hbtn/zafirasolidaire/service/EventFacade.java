package com.hbtn.zafirasolidaire.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hbtn.zafirasolidaire.dto.EventDto;
import com.hbtn.zafirasolidaire.dto.RequestEventDto;
import com.hbtn.zafirasolidaire.dto.RequestPhotoDto;
import com.hbtn.zafirasolidaire.mapper.EventMapper;
import com.hbtn.zafirasolidaire.model.Event;
import com.hbtn.zafirasolidaire.model.EventCategory;
import com.hbtn.zafirasolidaire.model.Photo;
import com.hbtn.zafirasolidaire.repository.EventCategoryRepository;
import com.hbtn.zafirasolidaire.repository.EventRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EventFacade {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final PhotoFacade photoFacade;
    private final EventCategoryRepository eventCategoryRepository;

    @Autowired
    public EventFacade(EventMapper eventMapper, EventRepository eventRepository, PhotoFacade photoFacade, EventCategoryRepository eventCategoryRepository) {
        this.eventMapper = eventMapper;
        this.eventRepository = eventRepository;
        this.photoFacade = photoFacade;
        this.eventCategoryRepository = eventCategoryRepository;
    }

    //---------- Repository Services ----------//
    public void createEvent(RequestEventDto requestEventDto) {
        if (requestEventDto == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }

        Event event = eventMapper.requestDtoToEvent(requestEventDto);

        // Fetch category
        EventCategory category = eventCategoryRepository
                                    .findByName(requestEventDto.getCategory())
                                    .orElseThrow(() -> new RuntimeException("Category not found"));
        event.setCategory(category);

        // Handle photo safely
        if (requestEventDto.getPhotoUrl() == null || requestEventDto.getPhotoUrl().isBlank()) {
            event.setPhoto(null);
        }

        eventRepository.save(event);
    }


    public void updateEvent(UUID id, RequestEventDto requestEventDto) {
        if (id == null) {
            throw new IllegalArgumentException("Event Id cannot be null");
        } else if (!eventRepository.existsById(id)) {
            throw new IllegalArgumentException("Event must already exist");
        }

        Event foundEvent = eventRepository.findById(id)
                                     .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        foundEvent.setDate(requestEventDto.getDate())
                  .setCapacity(requestEventDto.getCapacity())
                  .setCategory(foundEvent.getCategory().setName(requestEventDto.getCategory()));

        eventRepository.save(foundEvent);
    }

    public void createAllEvents(Iterable<RequestEventDto> requestEventDtos) {
        if (requestEventDtos == null || !requestEventDtos.iterator().hasNext()) {
            throw new IllegalArgumentException("Event list cannot be null or empty.");
        }

        List<Event> events = new ArrayList<>();

        for (RequestEventDto requestEventDto : requestEventDtos) {
            Event event = eventMapper.requestDtoToEvent(requestEventDto);
            events.add(event);
        }
        eventRepository.saveAll(events);
    }

    public void addPhoto(RequestPhotoDto photoDto, UUID eventId) {
        if (photoDto == null) {
            throw new IllegalArgumentException("Photo DTO cannot be null.");
        } else if (eventId == null) {
            throw new IllegalArgumentException("Event ID cannot be null.");
        }

        Event event = eventRepository.findById(eventId)
                                     .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        // Create and persist Photo
        Photo photo = photoFacade.mapDtoToPhoto(photoDto);
        photo.setEvent(event);
        photoFacade.savePhoto(photo);

        // Link Photo to Event
        event.setPhoto(photo);
        eventRepository.save(event);
    }

    public EventDto getEventById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Event ID cannot be null.");
        }

        Event foundEvent = eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return eventMapper.eventToDto(foundEvent);
    }

    public boolean existsById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Event ID cannot be null.");
        }
        return eventRepository.existsById(id);
    }

    public Iterable<EventDto> getAllEvents() {
        return mapToDto(eventRepository.findAll());
    }

    public Iterable<EventDto> getAllEventsById(Iterable<UUID> ids) {
        if (ids == null || !ids.iterator().hasNext()) {
            throw new IllegalArgumentException("ID list cannot be null or empty.");
        }

        return mapToDto(eventRepository.findAllById(ids));
    }

    public void deleteEventById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Event ID cannot be null.");
        }
        eventRepository.deleteById(id);
    }

    public void deleteEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }
        eventRepository.delete(event);
    }

    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }

    @Transactional
    public void deleteCategorybyId(UUID id) {
        EventCategory category = eventCategoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        if (eventRepository.existsByCategoryId(id)) {
            throw new IllegalStateException("Cannot delete category with existing events");
        }

    eventCategoryRepository.delete(category);
}

    public void deleteAllEvents(Iterable<Event> events) {
        if (events == null || !events.iterator().hasNext()) {
            throw new IllegalArgumentException("Event list cannot be null or empty.");
        }
        eventRepository.deleteAll(events);
    }

    public long countEvents() {
        return eventRepository.count();
    }

    //--------- Helper methods ---------//

    private Iterable<EventDto> mapToDto(Iterable<Event> events) {
        return StreamSupport.stream(events.spliterator(), false)
            .map(eventMapper::eventToDto)
            .collect(Collectors.toList());
    }

}
