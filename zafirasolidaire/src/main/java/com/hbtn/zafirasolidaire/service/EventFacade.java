package com.hbtn.zafirasolidaire.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.dto.EventDto;
import com.hbtn.zafirasolidaire.mapper.EventMapper;
import com.hbtn.zafirasolidaire.model.Event;
import com.hbtn.zafirasolidaire.repository.EventRepository;

@Service
public class EventFacade {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Autowired
    public EventFacade(EventMapper eventMapper, EventRepository eventRepository) {
        this.eventMapper = eventMapper;
        this.eventRepository = eventRepository;
    }

    //---------- Repository Services ----------//
    public void createEvent(EventDto eventDto) {
        if (eventDto == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }

        Event event = eventMapper.dtoToEvent(eventDto);

        eventRepository.save(event);
    }

    public void createAllUsers(Iterable<EventDto> eventDtos) {
        if (eventDtos == null || !eventDtos.iterator().hasNext()) {
            throw new IllegalArgumentException("Event list cannot be null or empty.");
        }

        List<Event> events = new ArrayList<>();

        for (EventDto eventDto : eventDtos) {
            Event event = eventMapper.dtoToEvent(eventDto);
            events.add(event);
        }
        eventRepository.saveAll(events);
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
