package com.hbtn.zafirasolidaire.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.hbtn.zafirasolidaire.dto.EventDto;
import com.hbtn.zafirasolidaire.model.Event;
import com.hbtn.zafirasolidaire.model.EventCategory;
import com.hbtn.zafirasolidaire.model.Photo;

public class EventMapperTest {

    static class BaseEventTest extends Event{

        public BaseEventTest setId(UUID id) {
            super.id = id;
            return this;
        }
    }

    private final EventMapper mapper = Mappers.getMapper(EventMapper.class);

    @Test
    public void testEventToDto() {
        // Arrange
        UUID id = UUID.randomUUID();
        EventCategory category = new EventCategory();
        category.setName("Workshop");

        Photo photo = new Photo();
        photo.setUrl("https://example.com/photo.jpg");

        Event event = new BaseEventTest()
                .setId(id)
                .setCategory(category)
                .setDate(LocalDateTime.of(2025, 10, 10, 14, 0))
                .setCapacity(50)
                .setPhoto(photo);

        // Act
        EventDto dto = mapper.eventToDto(event);

        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getEventId()).isEqualTo(id);
        assertThat(dto.getCategory()).isEqualTo("Workshop");
        assertThat(dto.getDate()).isEqualTo(LocalDateTime.of(2025, 10, 10, 14, 0));
        assertThat(dto.getCapacity()).isEqualTo(50);
        assertThat(dto.getPhotoUrl()).isEqualTo("https://example.com/photo.jpg");

        System.out.println("Date of the event: " + event.getDate().getDayOfMonth() + "th of "
                                                 + event.getDate().getMonth() + " "
                                                 + event.getDate().getYear() + " at "
                                                 + event.getDate().getHour() + ":"
                                                 + event.getDate().getMinute());
    }

    @Test
    public void testdtoToEvent() {
        // Arrange
        UUID id = UUID.randomUUID();
        EventDto dto = new EventDto();
        dto.setEventId(id);
        dto.setCategory("Seminar");
        dto.setDate(LocalDateTime.of(2025, 11, 20, 9, 30));
        dto.setCapacity(100);
        dto.setPhotoUrl("https://example.com/seminar.jpg");

        // Act
        Event event = mapper.dtoToEvent(dto);

        // Assert
        assertThat(event).isNotNull();
        assertThat(event.getId()).isNull();
        assertThat(event.getCategory()).isNotNull();
        assertThat(event.getCategory().getName()).isEqualTo("Seminar");
        assertThat(event.getDate()).isEqualTo(LocalDateTime.of(2025, 11, 20, 9, 30));
        assertThat(event.getCapacity()).isEqualTo(100);
        assertThat(event.getPhoto()).isNotNull();
        assertThat(event.getPhoto().getUrl()).isEqualTo("https://example.com/seminar.jpg");
    }
}
