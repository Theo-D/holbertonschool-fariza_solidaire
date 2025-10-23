package com.hbtn.zafirasolidaire.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hbtn.zafirasolidaire.model.Event;
import com.hbtn.zafirasolidaire.model.EventCategory;
import com.hbtn.zafirasolidaire.model.Photo;
import com.hbtn.zafirasolidaire.repository.EventCategoryRepository;
import com.hbtn.zafirasolidaire.repository.EventRepository;

@Component
public class EventInitializer implements CommandLineRunner{

    private final EventCategoryRepository eventCategoryRepository;
    private final EventRepository eventRepository;

    public EventInitializer(EventCategoryRepository eventCategoryRepository, EventRepository eventRepository) {
        this.eventCategoryRepository = eventCategoryRepository;
        this.eventRepository = eventRepository;
    };

    @Override
    public void run(String... args) throws Exception {

        List<EventCategory> categories = List.of(
            new EventCategory().setName("Atelier CV"),
            new EventCategory().setName("Atelier Photo"),
            new EventCategory().setName("Pot de départ"),
            new EventCategory().setName("Rencontres entreprises")
        );

        for (EventCategory eventCategory : categories) {
            eventCategoryRepository.save(eventCategory);
        }

        List<Event> events = List.of(
            new Event().setCategory(eventCategoryRepository.findByName("Atelier CV").orElseThrow(() -> new RuntimeException("Category not found")))
                    .setDate(LocalDateTime.now().plusDays(5))
                    .setCapacity(100)
                    .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),
            new Event().setCategory(eventCategoryRepository.findByName("Atelier CV").orElseThrow(() -> new RuntimeException("Category not found")))
                    .setDate(LocalDateTime.now().plusDays(10))
                    .setCapacity(250)
                    .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),
            new Event().setCategory(eventCategoryRepository.findByName("Atelier CV").orElseThrow(() -> new RuntimeException("Category not found")))
                    .setDate(LocalDateTime.now().plusDays(8))
                    .setCapacity(80)
                    .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),
            new Event().setCategory(eventCategoryRepository.findByName("Atelier Photo").orElseThrow(() -> new RuntimeException("Category not found")))
                    .setDate(LocalDateTime.now().plusDays(15))
                    .setCapacity(120)
                    .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),
            new Event().setCategory(eventCategoryRepository.findByName("Atelier Photo").orElseThrow(() -> new RuntimeException("Category not found")))
                    .setDate(LocalDateTime.now().plusDays(3))
                    .setCapacity(50)
                    .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),
            new Event().setCategory(eventCategoryRepository.findByName("Atelier Photo").orElseThrow(() -> new RuntimeException("Category not found")))
                    .setDate(LocalDateTime.now().plusDays(25))
                    .setCapacity(200)
                    .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),
            new Event().setCategory(eventCategoryRepository.findByName("Pot de départ").orElseThrow(() -> new RuntimeException("Category not found")))
                    .setDate(LocalDateTime.now().plusDays(7))
                    .setCapacity(40)
                    .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),
            new Event().setCategory(eventCategoryRepository.findByName("Pot de départ").orElseThrow(() -> new RuntimeException("Category not found")))
                    .setDate(LocalDateTime.now().plusDays(12))
                    .setCapacity(60)
                    .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),
            new Event().setCategory(eventCategoryRepository.findByName("Pot de départ").orElseThrow(() -> new RuntimeException("Category not found")))
                    .setDate(LocalDateTime.now().plusDays(18))
                    .setCapacity(30)
                    .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),
            new Event().setCategory(eventCategoryRepository.findByName("Rencontres entreprises").orElseThrow(() -> new RuntimeException("Category not found")))
                    .setDate(LocalDateTime.now().plusDays(2))
                    .setCapacity(25)
                    .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),
            new Event().setCategory(eventCategoryRepository.findByName("Rencontres entreprises").orElseThrow(() -> new RuntimeException("Category not found")))
                    .setDate(LocalDateTime.now().plusDays(9))
                    .setCapacity(70)
                    .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),
            new Event().setCategory(eventCategoryRepository.findByName("Rencontres entreprises").orElseThrow(() -> new RuntimeException("Category not found")))
                    .setDate(LocalDateTime.now().plusDays(14))
                    .setCapacity(45)
                    .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100"))
        );

        for (Event event : events) {
            eventRepository.save(event);
        }

    }

}
