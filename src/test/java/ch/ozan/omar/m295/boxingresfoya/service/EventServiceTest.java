package ch.ozan.omar.m295.boxingresfoya.service;
import ch.ozan.omar.m295.boxingresfoya.entity.Event;
import ch.ozan.omar.m295.boxingresfoya.repository.EventRepository;
import ch.ozan.omar.m295.boxingresfoya.service.EventService;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the EventService class.
 */
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests the getAllEvents method.
     */
    @Test
    void getAllEventsShouldReturnListOfEvents() {
        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(new Event());
        expectedEvents.add(new Event());
        when(eventRepository.findAll()).thenReturn(expectedEvents);
        List<Event> actualEvents = eventService.getAllEvents();
        assertEquals(expectedEvents.size(), actualEvents.size());
    }

    /**
     * Tests the getEvent method when the event exists.
     */
    @Test
    void getEventShouldReturnEventIfExists() {
        Long id = 1L;
        Event expectedEvent = new Event();
        when(eventRepository.findById(id)).thenReturn(Optional.of(expectedEvent));
        Event actualEvent = eventService.getEvent(id);
        assertEquals(expectedEvent, actualEvent);
    }

    /**
     * Tests the getEvent method when the event does not exist.
     */
    @Test
    void getEventShouldThrowEntityNotFoundExceptionIfEventDoesNotExist() {
        Long id = 1L;
        when(eventRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> eventService.getEvent(id));
    }

    /**
     * Tests the createEvent method.
     */
    @Test
    void createEventShouldReturnCreatedEvent() {
        Event eventToCreate = new Event();
        when(eventRepository.save(eventToCreate)).thenReturn(eventToCreate);
        Event createdEvent = eventService.createEvent(eventToCreate);
        assertEquals(eventToCreate, createdEvent);
    }

    /**
     * Tests the updateEvent method when the event exists.
     */
    @Test
    void updateEventShouldReturnUpdatedEvent() {
        Long id = 1L;
        Event existingEvent = new Event();
        Event updatedEvent = new Event();
        when(eventRepository.findById(id)).thenReturn(Optional.of(existingEvent));
        when(eventRepository.save(updatedEvent)).thenReturn(updatedEvent);
        Event result = eventService.updateEvent(updatedEvent, id);
        assertEquals(updatedEvent, result);
    }

    /**
     * Tests the updateEvent method when the event does not exist.
     */
    @Test
    void updateEventShouldThrowEntityNotFoundExceptionIfEventDoesNotExist() {
        Long id = 1L;
        Event updatedEvent = new Event();
        when(eventRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> eventService.updateEvent(updatedEvent, id));
    }

    /**
     * Tests the deleteEvent method when the event exists.
     */
    @Test
    void deleteEventShouldDeleteEventIfExists() {
        Long id = 1L;
        when(eventRepository.existsById(id)).thenReturn(true);
        eventService.deleteEvent(id);
        verify(eventRepository, times(1)).deleteById(id);
    }

    /**
     * Tests the deleteEvent method when the event does not exist.
     */
    @Test
    void deleteEventShouldThrowEntityNotFoundExceptionIfEventDoesNotExist() {
        Long id = 1L;
        when(eventRepository.existsById(id)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> eventService.deleteEvent(id));
    }
}

