package ch.ozan.omar.m295.boxingresfoya.service;

import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.repository.EventRepository;
import ch.ozan.omar.m295.boxingresfoya.entity.Event;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for managing Event entities.
 */
@Service
public class EventService {

    private final EventRepository eventRepository;

    /**
     * Constructs a new EventService with the provided EventRepository.
     * @param eventRepository The repository for Event entities.
     */
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Retrieves all Event entities.
     * @return A list of all Event entities.
     */
    @RolesAllowed(Roles.Read)
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    /**
     * Retrieves a single Event entity by its ID.
     * @param id The ID of the Event entity to retrieve.
     * @return The requested Event entity.
     * @throws EntityNotFoundException If the Event with the given ID is not found.
     */
    @RolesAllowed(Roles.Read)
    public Event getEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Event.class));
    }

    /**
     * Creates a new Event entity.
     * @param event The Event entity to create.
     * @return The newly created Event entity.
     */
    @RolesAllowed(Roles.Admin)
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    /**
     * Updates an existing Event entity.
     * @param event The updated Event entity.
     * @param id The ID of the Event entity to update.
     * @return The updated Event entity.
     * @throws EntityNotFoundException If the Event with the given ID is not found.
     */
    @RolesAllowed(Roles.Read)
    public Event updateEvent(Event event, Long id) {
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    event.setId(id);
                    return eventRepository.save(event);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Event.class));
    }

    /**
     * Deletes an Event entity by its ID.
     * @param id The ID of the Event entity to delete.
     * @throws EntityNotFoundException If the Event with the given ID is not found.
     */
    @RolesAllowed(Roles.Admin)
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException(id, Event.class);
        }
        eventRepository.deleteById(id);
    }
}

