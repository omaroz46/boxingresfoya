package ch.ozan.omar.m295.boxingresfoya.controller;
import ch.ozan.omar.m295.boxingresfoya.entity.Event;
import ch.ozan.omar.m295.boxingresfoya.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the EventController class.
 */
public class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests the getAll method of EventController.
     */

    @Test
    void testGetAllEvents() {
        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(new Event());
        expectedEvents.add(new Event());
        when(eventService.getAllEvents()).thenReturn(expectedEvents);
        ResponseEntity<List<Event>> responseEntity = eventController.all();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedEvents.size(), responseEntity.getBody().size());
    }

    /**
     * Tests the one method of EventController.
     */
    @Test
    void testGetEventById() {
        Long id = 1L;
        Event expectedEvent = new Event();
        when(eventService.getEvent(id)).thenReturn(expectedEvent);
        ResponseEntity<Event> responseEntity = eventController.one(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedEvent, responseEntity.getBody());
    }

    /**
     * Tests the newEvent method of EventController.
     */
    @Test
    void testCreateEvent() {
        Event event = new Event();
        when(eventService.createEvent(event)).thenReturn(event);
        ResponseEntity<Event> responseEntity = eventController.newEvent(event);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(event, responseEntity.getBody());
    }

    /**
     * Tests the updateEvent method of EventController.
     */
    @Test
    void testUpdateEvent() {
        Long id = 1L;
        Event event = new Event();
        when(eventService.updateEvent(event, id)).thenReturn(event);
        ResponseEntity<Event> responseEntity = eventController.updateEvent(event, id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(event, responseEntity.getBody());
    }

    /**
     * Tests the deleteEvent method of EventController.
     */
    @Test
    void testDeleteEvent() {
        Long id = 1L;
        ResponseEntity<?> responseEntity = eventController.deleteEvent(id);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(eventService, times(1)).deleteEvent(id);
    }
}

