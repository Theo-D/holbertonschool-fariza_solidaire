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
public class EventInitializer implements CommandLineRunner {

    private final EventCategoryRepository eventCategoryRepository;
    private final EventRepository eventRepository;

    public EventInitializer(EventCategoryRepository eventCategoryRepository, EventRepository eventRepository) {
        this.eventCategoryRepository = eventCategoryRepository;
        this.eventRepository = eventRepository;
    };

    @Override
    public void run(String...args) throws Exception {

        List < EventCategory > categories = List.of(
            new EventCategory().setName("Vestiaire professionnel"),
            new EventCategory().setName("Atelier maquillage naturel"),
            new EventCategory().setName("Shooting photo pro"),
            new EventCategory().setName("Coaching image personnalisé")
        );

        for (EventCategory eventCategory: categories) {
            eventCategoryRepository.save(eventCategory);
        }

        List < Event > events = List.of(
            new Event().setCategory(eventCategoryRepository.findByName("Vestiaire professionnel").orElseThrow(() -> new RuntimeException("Category not found")))
            .setDate(LocalDateTime.now().plusDays(5))
            .setCapacity(100)
            .setUrl("https://example.com/event1")
            .setDescription("Join us for a professional wardrobe session to refresh your style and confidence.")
            .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),

            new Event().setCategory(eventCategoryRepository.findByName("Atelier maquillage naturel").orElseThrow(() -> new RuntimeException("Category not found")))
            .setDate(LocalDateTime.now().plusDays(10))
            .setCapacity(250)
            .setUrl("https://example.com/event2")
            .setDescription("Learn natural makeup techniques in this hands-on workshop with experts.")
            .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),

            new Event().setCategory(eventCategoryRepository.findByName("Shooting photo pro").orElseThrow(() -> new RuntimeException("Category not found")))
            .setDate(LocalDateTime.now().plusDays(8))
            .setCapacity(80)
            .setUrl("https://example.com/event3")
            .setDescription("Get professional photos taken in a fully equipped studio environment.")
            .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),

            new Event().setCategory(eventCategoryRepository.findByName("Coaching image personnalisé").orElseThrow(() -> new RuntimeException("Category not found")))
            .setDate(LocalDateTime.now().plusDays(15))
            .setCapacity(120)
            .setUrl("https://example.com/event4")
            .setDescription("Receive one-on-one personalized image coaching to enhance your personal brand.")
            .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),

            new Event().setCategory(eventCategoryRepository.findByName("Vestiaire professionnel").orElseThrow(() -> new RuntimeException("Category not found")))
            .setDate(LocalDateTime.now().plusDays(3))
            .setCapacity(50)
            .setUrl("https://example.com/event5")
            .setDescription("A short session to organize and optimize your professional wardrobe.")
            .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),

            new Event().setCategory(eventCategoryRepository.findByName("Shooting photo pro").orElseThrow(() -> new RuntimeException("Category not found")))
            .setDate(LocalDateTime.now().plusDays(25))
            .setCapacity(200)
            .setUrl("https://example.com/event6")
            .setDescription("Advanced professional photoshoot for personal or business portfolios.")
            .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),

            new Event().setCategory(eventCategoryRepository.findByName("Atelier maquillage naturel").orElseThrow(() -> new RuntimeException("Category not found")))
            .setDate(LocalDateTime.now().plusDays(7))
            .setCapacity(40)
            .setUrl("https://example.com/event7")
            .setDescription("Hands-on natural makeup session to bring out your best features.")
            .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100")),

            new Event().setCategory(eventCategoryRepository.findByName("Coaching image personnalisé").orElseThrow(() -> new RuntimeException("Category not found")))
            .setDate(LocalDateTime.now().plusDays(12))
            .setCapacity(60)
            .setUrl("https://example.com/event8")
            .setDescription("Personalized coaching to refine your image for professional success.")
            .setPhoto(new Photo().setUrl("https://www.placeholderimage.eu/api/100/100"))
        );

        for (Event event: events) {
            eventRepository.save(event);
        }
    }
}
