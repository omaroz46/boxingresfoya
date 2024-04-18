package ch.ozan.omar.m295.boxingresfoya.repository;

import ch.ozan.omar.m295.boxingresfoya.entity.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void createShouldContainNewlyCreatedEvent() {
        // Create
        LocalDateTime date = LocalDateTime.now();
        Event event = new Event(date, "Test Venue", "Test Description");
        eventRepository.save(event);

        Optional<Event> savedEventOptional = eventRepository.findById(event.getId());
        assertTrue(savedEventOptional.isPresent());
        Event savedEvent = savedEventOptional.get();
        assertEquals(date, savedEvent.getDate());
        assertEquals("Test Venue", savedEvent.getVenue());
        assertEquals("Test Description", savedEvent.getDescription());
    }

    @Test
    public void readShouldContainPreviouslyCreatedEvent() {
        // Create
        LocalDateTime date = LocalDateTime.now();
        Event event = new Event(date, "Test Venue", "Test Description");
        eventRepository.save(event);

        // Read
        Optional<Event> savedEventOptional = eventRepository.findById(event.getId());
        assertTrue(savedEventOptional.isPresent());
        Event savedEvent = savedEventOptional.get();
        assertEquals(date, savedEvent.getDate());
        assertEquals("Test Venue", savedEvent.getVenue());
        assertEquals("Test Description", savedEvent.getDescription());
    }

    @Test
    public void updateShouldContainUpdatedEvent() {
        // Create
        LocalDateTime date = LocalDateTime.now();
        Event event = new Event(date, "Test Venue", "Test Description");
        eventRepository.save(event);

        // Update
        Optional<Event> savedEventOptional = eventRepository.findById(event.getId());
        assertTrue(savedEventOptional.isPresent());
        Event savedEvent = savedEventOptional.get();
        savedEvent.setVenue("Updated Venue");
        savedEvent.setDescription("Updated Description");
        eventRepository.save(savedEvent);

        Optional<Event> updatedEventOptional = eventRepository.findById(savedEvent.getId());
        assertTrue(updatedEventOptional.isPresent());
        Event updatedEvent = updatedEventOptional.get();
        assertEquals("Updated Venue", updatedEvent.getVenue());
        assertEquals("Updated Description", updatedEvent.getDescription());
    }

    @Test
    public void deleteShouldNotContainDeletedEvent() {
        // Create
        LocalDateTime date = LocalDateTime.now();
        Event event = new Event(date, "Test Venue", "Test Description");
        eventRepository.save(event);

        // Delete
        eventRepository.delete(event);
        Optional<Event> deletedEventOptional = eventRepository.findById(event.getId());
        assertTrue(deletedEventOptional.isEmpty());
    }
}
