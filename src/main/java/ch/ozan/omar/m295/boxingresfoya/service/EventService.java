package ch.ozan.omar.m295.boxingresfoya.service;

import ch.ozan.omar.m295.boxingresfoya.entity.Event;
import ch.ozan.omar.m295.boxingresfoya.repository.EventRepository;
import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing Event resources.
 */
@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    private final EventRepository eventRepository;

    /**
     * Constructs an EventService with the specified EventRepository.
     *
     * @param eventRepository the EventRepository to be used
     */
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Retrieves all events.
     *
     * @return a list of all events
     */
    @RolesAllowed(Roles.Read)
    public List<Event> getAllEvents() {
        logger.info("Fetching all events");
        return eventRepository.findAll();
    }

    /**
     * Retrieves an event by its ID.
     *
     * @param id the ID of the event
     * @return the event with the specified ID
     * @throws EntityNotFoundException if the event is not found
     */
    @RolesAllowed(Roles.Read)
    public Event getEvent(Long id) {
        logger.info("Fetching event with ID: {}", id);
        return eventRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Event with ID: {} not found", id);
                    return new EntityNotFoundException(id, Event.class);
                });
    }

    /**
     * Creates a new event.
     *
     * @param event the event to be created
     * @return the created event
     */
    @RolesAllowed(Roles.Admin)
    public Event createEvent(Event event) {
        logger.info("Creating event: {}", event);
        return eventRepository.save(event);
    }

    /**
     * Updates an existing event.
     *
     * @param event the event with updated information
     * @param id the ID of the event to be updated
     * @return the updated event
     * @throws EntityNotFoundException if the event is not found
     */
    @RolesAllowed(Roles.Read)
    public Event updateEvent(Event event, Long id) {
        logger.info("Updating event with ID: {} with data: {}", id, event);
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    event.setId(id);
                    return eventRepository.save(event);
                })
                .orElseThrow(() -> {
                    logger.error("Event with ID: {} not found", id);
                    return new EntityNotFoundException(id, Event.class);
                });
    }

    /**
     * Deletes an event by its ID.
     *
     * @param id the ID of the event to be deleted
     * @throws EntityNotFoundException if the event is not found
     */
    @RolesAllowed(Roles.Admin)
    public void deleteEvent(Long id) {
        logger.info("Deleting event with ID: {}", id);
        if (!eventRepository.existsById(id)) {
            logger.error("Event with ID: {} not found", id);
            throw new EntityNotFoundException(id, Event.class);
        }
        eventRepository.deleteById(id);
    }
}
