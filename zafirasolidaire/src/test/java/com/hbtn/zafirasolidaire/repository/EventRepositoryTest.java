package com.hbtn.zafirasolidaire.repository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.hbtn.zafirasolidaire.config.JpaConfigTest;
import com.hbtn.zafirasolidaire.model.Event;
import com.hbtn.zafirasolidaire.model.EventCategory;
import com.hbtn.zafirasolidaire.model.Photo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import(JpaConfigTest.class)
public class EventRepositoryTest {

    @Autowired
    EventRepository eventRepository;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private Event createValidEvent() {
        EventCategory eventCategory = new EventCategory().setName("CV Workshop");
        Event event = new Event().setCategory(eventCategory)
                                 .setDate(LocalDateTime.now().plusDays(1))
                                 .setCapacity(12)
                                 .setPhoto(new Photo());
        return event;
    }

    @Test
    void testSaveValidEvent() {
        // Arrange
        Event event = createValidEvent();
        // Act
        Event saved = eventRepository.save(event);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(event.getCategory(), saved.getCategory());
    }

    @Test
    void testValidationFailsWhenCategoryIsNull() {
        // Arrange
        Event event = createValidEvent();
        event.setCategory(null);

        // Act
        Set<ConstraintViolation<Event>> violations = validator.validate(event);

        // Assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("category")
                        && v.getMessage().contains("must not be null")));
    }

    @Test
    void testValidationFailsWhenDateIsInPast() {
        // Arrange
        Event event = createValidEvent();
        event.setDate(LocalDateTime.now().minusDays(1));

        // Act
        Set<ConstraintViolation<Event>> violations = validator.validate(event);

        // Assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("date")
                        && v.getMessage().contains("must be in the future")));
    }

    @Test
    void testValidationFailsWhenCapacityIsLessThanOne() {
        // Arrange
        Event event = createValidEvent();
        event.setCapacity(0);

        System.out.println("Event Capacity: " + event.getCapacity());

        // Act
        Set<ConstraintViolation<Event>> violations = validator.validate(event);

        // Assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath()
                                      .toString().equals("capacity") && v.getMessage()
                                                                         .contains("Events must host at least one person")));
    }

    @Test
    void testSaveEventWithPhoto() {
        // Arrange
        Event event = createValidEvent();
        Photo photo = new Photo().setUrl("https://zafirasolidaire/img/eventPhoto.jpg");
        event.setPhoto(photo);

        // Act
        Event saved = eventRepository.save(event);

        // Assert
        assertNotNull(saved.getPhoto());
    }
}
