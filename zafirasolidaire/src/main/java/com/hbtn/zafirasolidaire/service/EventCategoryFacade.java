package com.hbtn.zafirasolidaire.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.dto.EventCategoryDto;
import com.hbtn.zafirasolidaire.dto.RequestEventCategoryDto;
import com.hbtn.zafirasolidaire.mapper.EventCategoryMapper;
import com.hbtn.zafirasolidaire.model.EventCategory;
import com.hbtn.zafirasolidaire.repository.EventCategoryRepository;

@Service
public class EventCategoryFacade {

    private final EventCategoryRepository eventCategoryRepository;
    private final EventCategoryMapper eventCategoryMapper;

    @Autowired
    public EventCategoryFacade(EventCategoryRepository eventCategoryRepository, EventCategoryMapper eventCategoryMapper) {
        this.eventCategoryRepository = eventCategoryRepository;
        this.eventCategoryMapper = eventCategoryMapper;
    }

    //---------- Repository Services ----------//
    public void createEvent(RequestEventCategoryDto requestEventCategoryDto) {
        if (requestEventCategoryDto == null) {
            throw new IllegalArgumentException("EventCategory cannot be null.");
        }

        EventCategory eventcategory = eventCategoryMapper.requestDtoToEventCategory(requestEventCategoryDto);

        eventCategoryRepository.save(eventcategory);
    }

    public void createAllEvents(Iterable<RequestEventCategoryDto> requestEventCategoryDtos) {
        if (requestEventCategoryDtos == null || !requestEventCategoryDtos.iterator().hasNext()) {
            throw new IllegalArgumentException("EventCategory list cannot be null or empty.");
        }

        List<EventCategory> events = new ArrayList<>();

        for (RequestEventCategoryDto requestEventCategoryDto : requestEventCategoryDtos) {
            EventCategory eventcategory = eventCategoryMapper.requestDtoToEventCategory(requestEventCategoryDto);
            events.add(eventcategory);
        }
        eventCategoryRepository.saveAll(events);
    }


    public EventCategoryDto getEventById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("EventCategory ID cannot be null.");
        }

        EventCategory foundEvent = eventCategoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return eventCategoryMapper.eventCategoryToDto(foundEvent);
    }

    public boolean existsById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("EventCategory ID cannot be null.");
        }
        return eventCategoryRepository.existsById(id);
    }

    public Iterable<EventCategoryDto> getAllEvents() {
        return mapToDto(eventCategoryRepository.findAll());
    }

    public Iterable<EventCategoryDto> getAllEventsById(Iterable<UUID> ids) {
        if (ids == null || !ids.iterator().hasNext()) {
            throw new IllegalArgumentException("ID list cannot be null or empty.");
        }

        return mapToDto(eventCategoryRepository.findAllById(ids));
    }

    public void deleteEventById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("EventCategory ID cannot be null.");
        }
        eventCategoryRepository.deleteById(id);
    }

    public void deleteEvent(EventCategory eventcategory) {
        if (eventcategory == null) {
            throw new IllegalArgumentException("EventCategory cannot be null.");
        }
        eventCategoryRepository.delete(eventcategory);
    }

    public void deleteAllEvents() {
        eventCategoryRepository.deleteAll();
    }

    public void deleteAllEvents(Iterable<EventCategory> eventCategories) {
        if (eventCategories == null || !eventCategories.iterator().hasNext()) {
            throw new IllegalArgumentException("EventCategory list cannot be null or empty.");
        }
        eventCategoryRepository.deleteAll(eventCategories);
    }

    public long countEvents() {
        return eventCategoryRepository.count();
    }

    //--------- Helper methods ---------//

    private Iterable<EventCategoryDto> mapToDto(Iterable<EventCategory> eventCategories) {
        return StreamSupport.stream(eventCategories.spliterator(), false)
                            .map(eventCategoryMapper::eventCategoryToDto)
                            .collect(Collectors.toList());
    }
}
