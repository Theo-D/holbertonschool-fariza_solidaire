package com.hbtn.zafirasolidaire.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbtn.zafirasolidaire.dto.EventDto;
import com.hbtn.zafirasolidaire.mapper.EventMapper;
import com.hbtn.zafirasolidaire.model.Event;
import com.hbtn.zafirasolidaire.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
public class EventFacadeTest {

    @Mock
    private EventMapper eventMapper;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventFacade eventFacade;

    private EventDto eventDto;
    private Event event;
    private UUID eventId;

    @BeforeEach
    void setUp() {
        eventId = UUID.randomUUID();
        eventDto = new EventDto();
        event = new Event();
    }

    // createEvent
    @Test
    void createEvent_shouldSaveEvent() {
        when(eventMapper.dtoToEvent(eventDto)).thenReturn(event);
        eventFacade.createEvent(eventDto);
        verify(eventRepository).save(event);
    }

    @Test
    void createEvent_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> eventFacade.createEvent(null));
    }

    // createAllUsers
    @Test
    void createAllUsers_shouldSaveAllEvents() {
        List<EventDto> dtos = List.of(eventDto);
        when(eventMapper.dtoToEvent(eventDto)).thenReturn(event);
        eventFacade.createAllEvents(dtos);
        verify(eventRepository).saveAll(List.of(event));
    }

    @Test
    void createAllUsers_shouldThrowException_whenNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> eventFacade.createAllEvents(null));
        assertThrows(IllegalArgumentException.class, () -> eventFacade.createAllEvents(List.of()));
    }

    // getEventById
    @Test
    void getEventById_shouldReturnEventDto() {
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventMapper.eventToDto(event)).thenReturn(eventDto);

        EventDto result = eventFacade.getEventById(eventId);

        assertEquals(eventDto, result);
    }

    @Test
    void getEventById_shouldThrowException_whenNotFound() {
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> eventFacade.getEventById(eventId));
    }

    @Test
    void getEventById_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> eventFacade.getEventById(null));
    }

    // existsById
    @Test
    void existsById_shouldReturnTrueIfExists() {
        when(eventRepository.existsById(eventId)).thenReturn(true);
        assertTrue(eventFacade.existsById(eventId));
    }

    @Test
    void existsById_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> eventFacade.existsById(null));
    }

    // getAllEvents
    @Test
    void getAllEvents_shouldReturnDtoList() {
        List<Event> events = List.of(event);
        when(eventRepository.findAll()).thenReturn(events);
        when(eventMapper.eventToDto(event)).thenReturn(eventDto);

        Iterable<EventDto> result = eventFacade.getAllEvents();

        assertEquals(List.of(eventDto), result);
    }

    // getAllEventsById
    @Test
    void getAllEventsById_shouldReturnDtoList() {
        List<UUID> ids = List.of(eventId);
        List<Event> events = List.of(event);

        when(eventRepository.findAllById(ids)).thenReturn(events);
        when(eventMapper.eventToDto(event)).thenReturn(eventDto);

        Iterable<EventDto> result = eventFacade.getAllEventsById(ids);

        assertEquals(List.of(eventDto), result);
    }

    @Test
    void getAllEventsById_shouldThrowException_whenNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> eventFacade.getAllEventsById(null));
        assertThrows(IllegalArgumentException.class, () -> eventFacade.getAllEventsById(List.of()));
    }

    // deleteEventById
    @Test
    void deleteEventById_shouldCallRepository() {
        eventFacade.deleteEventById(eventId);
        verify(eventRepository).deleteById(eventId);
    }

    @Test
    void deleteEventById_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> eventFacade.deleteEventById(null));
    }

    // deleteEvent
    @Test
    void deleteEvent_shouldCallRepository() {
        eventFacade.deleteEvent(event);
        verify(eventRepository).delete(event);
    }

    @Test
    void deleteEvent_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> eventFacade.deleteEvent(null));
    }

    // deleteAllEvents
    @Test
    void deleteAllEvents_shouldCallRepository() {
        eventFacade.deleteAllEvents();
        verify(eventRepository).deleteAll();
    }

    @Test
    void deleteAllEvents_withIterable_shouldDelete() {
        List<Event> events = List.of(event);
        eventFacade.deleteAllEvents(events);
        verify(eventRepository).deleteAll(events);
    }

    @Test
    void deleteAllEvents_withIterable_shouldThrow_whenNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> eventFacade.deleteAllEvents(null));
        assertThrows(IllegalArgumentException.class, () -> eventFacade.deleteAllEvents(List.of()));
    }

    // countEvents
    @Test
    void countEvents_shouldReturnCount() {
        when(eventRepository.count()).thenReturn(42L);
        assertEquals(42L, eventFacade.countEvents());
    }
}
