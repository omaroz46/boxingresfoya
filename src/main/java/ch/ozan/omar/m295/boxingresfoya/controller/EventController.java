package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.entity.Event;
import ch.ozan.omar.m295.boxingresfoya.service.EventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * Controller for for managing Event entities.
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class EventController {

    private final EventService eventService;

    /**
     * Constructs a new EventController with the provided EventService.
     * @param eventService The service responsible for handling Event operations.
     */
    EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Retrieves all Event entities.
     * @return A ResponseEntity containing a list of all Event entities.
     */
    @GetMapping("/api/events")
    public ResponseEntity<List<Event>> all() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    /**
     * Retrieves a single Event entity by its ID.
     * @param id The ID of the Event entity to retrieve.
     * @return A ResponseEntity containing the requested Event entity.
     */
    @GetMapping("/api/events/{id}")
    public ResponseEntity<Event> one(@PathVariable Long id) {
        Event event = eventService.getEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    /**
     * Creates a new Event entity.
     * @param event The Event entity to create.
     * @return A ResponseEntity containing the newly created Event entity.
     */
    @PostMapping("/api/events")
    public ResponseEntity<Event> newEvent(@RequestBody Event event) {
        Event savedEvent = eventService.createEvent(event);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    /**
     * Updates an existing Event entity.
     * @param event The updated Event entity.
     * @param id The ID of the Event entity to update.
     * @return A ResponseEntity containing the updated Event entity.
     */
    @PutMapping("/api/events/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event, @PathVariable Long id) {
        Event updatedEvent = eventService.updateEvent(event, id);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    /**
     * Deletes an Event entity by its ID.
     * @param id The ID of the Event entity to delete.
     * @return A ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/api/events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

