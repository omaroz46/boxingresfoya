package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.entity.Event;
import ch.ozan.omar.m295.boxingresfoya.service.EventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Event resources.
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;

    /**
     * Constructs an EventController with the specified EventService.
     *
     * @param eventService the EventService to be used
     */
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Retrieves all events.
     *
     * @return a ResponseEntity containing the list of events
     */
    @GetMapping("/api/event")
    public ResponseEntity<List<Event>> all() {
        logger.info("Fetching all events");
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    /**
     * Retrieves an event by its ID.
     *
     * @param id the ID of the event
     * @return a ResponseEntity containing the event
     */
    @GetMapping("/api/event/{id}")
    public ResponseEntity<Event> one(@PathVariable Long id) {
        logger.info("Fetching event with ID: {}", id);
        Event event = eventService.getEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    /**
     * Creates a new event.
     *
     * @param event the event to be created
     * @return a ResponseEntity containing the created event
     */
    @PostMapping("/api/event")
    public ResponseEntity<Event> newEvent(@RequestBody Event event) {
        logger.info("Creating a new event: {}", event);
        Event savedEvent = eventService.createEvent(event);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    /**
     * Updates an existing event.
     *
     * @param event the event with updated information
     * @param id the ID of the event to be updated
     * @return a ResponseEntity containing the updated event
     */
    @PutMapping("/api/event/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event, @PathVariable Long id) {
        logger.info("Updating event with ID: {} with data: {}", id, event);
        Event updatedEvent = eventService.updateEvent(event, id);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    /**
     * Deletes an event by its ID.
     *
     * @param id the ID of the event to be deleted
     * @return a ResponseEntity with no content
     */
    @DeleteMapping("/api/event/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        logger.info("Deleting event with ID: {}", id);
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
