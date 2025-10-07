package com.hbtn.zafirasolidaire.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class EventTest {

    static class BaseEventTest extends Event{

        public BaseEventTest setId(UUID id) {
            super.id = id;
            return this;
        }
    }

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private EventCategory validCategory() {
        return new EventCategory().setName("Conference");
    }

    private Event validEvent() {
        return new Event()
                .setCategory(validCategory())
                .setDate(LocalDateTime.now().plusDays(1))
                .setCapacity(10)
                .setPhoto(null);
    }

    @Test
    public void testEventCreation () {
        //Arrange
        EventCategory eventCategory = new EventCategory();

        Photo photo = new Photo().setUrl("img/userPhoto.jpg");

        Event event = new BaseEventTest().setId(UUID.randomUUID())
                                         .setCategory(eventCategory)
                                         .setDate(LocalDateTime.now().plusDays(1))
                                         .setCapacity(12)
                                         .setPhoto(photo);

        // Act
        Set<ConstraintViolation<Event>> violations = validator.validate(event);

        // Assert
        assertThat(violations).isEmpty();

        assertThat(event.getCategory()).isEqualTo(eventCategory);
        assertThat(event.getDate()).isAfter(LocalDateTime.now());
        assertThat(event.getCapacity()).isEqualTo(12);
        assertThat(event.getPhoto()).isEqualTo(photo);
        assertThat(event.getId()).isNotNull();
    }

    @Test
    public void testValidEventShouldPassValidation() {
        // Arrange
        Event event = validEvent();

        // Act
        Set<ConstraintViolation<Event>> violations = validator.validate(event);

        // Assert
        assertThat(violations).isEmpty();
    }

    @Test
    public void testNullCategoryShouldFailValidation() {
        // Arrange
        Event event = validEvent().setCategory(null);

        // Act
        Set<ConstraintViolation<Event>> violations = validator.validate(event);

        // Assert
        assertThat(violations)
            .extracting(v -> v.getPropertyPath().toString())
            .contains("category");
    }

    @Test
    public void testPastDateShouldFailValidation() {
        // Arrange
        Event event = validEvent().setDate(LocalDateTime.now().minusDays(1));

        // Act
        Set<ConstraintViolation<Event>> violations = validator.validate(event);

        // Assert
        assertThat(violations)
            .extracting(v -> v.getPropertyPath().toString())
            .contains("date");
    }

    @Test
    public void testZeroCapacityShouldFailValidation() {
        // Arrange
        Event event = validEvent().setCapacity(0);

        // Act
        Set<ConstraintViolation<Event>> violations = validator.validate(event);

        // Assert
        assertThat(violations)
            .extracting(v -> v.getPropertyPath().toString())
            .contains("capacity");
    }

    @Test
    public void testNegativeCapacityShouldFailValidation() {
        // Arrange
        Event event = validEvent().setCapacity(-10);

        // Act
        Set<ConstraintViolation<Event>> violations = validator.validate(event);

        // Assert
        assertThat(violations)
            .extracting(v -> v.getPropertyPath().toString())
            .contains("capacity");
    }

    @Test
    public void testValidCategoryShouldPassValidation() {
        // Arrange
        EventCategory category = new EventCategory().setName("Music");

        // Act
        Set<ConstraintViolation<EventCategory>> violations = validator.validate(category);

        // Assert
        assertThat(violations).isEmpty();
    }

    @Test
    public void testNullNameShouldFailValidation() {
        // Arrange
        EventCategory category = new EventCategory().setName(null);

        // Act
        Set<ConstraintViolation<EventCategory>> violations = validator.validate(category);

        // Assert
        assertThat(violations)
            .extracting(v -> v.getPropertyPath().toString())
            .contains("name");
    }

    @Test
    public void testBlankNameShouldFailValidation() {
        // Arrange
        EventCategory category = new EventCategory().setName("   ");

        // Act
        Set<ConstraintViolation<EventCategory>> violations = validator.validate(category);

        // Assert
        assertThat(violations)
            .extracting(v -> v.getPropertyPath().toString())
            .contains("name");
    }
}
