package ch.ozan.omar.m295.boxingresfoya.service;

import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.repository.EventRepository;
import ch.ozan.omar.m295.boxingresfoya.entity.Event;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RolesAllowed(Roles.Read)
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @RolesAllowed(Roles.Read)
    public Event getEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Event.class));
    }

    @RolesAllowed(Roles.Admin)

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @RolesAllowed(Roles.Read)
    public Event updateEvent(Event event, Long id) {
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    event.setId(id);
                    return eventRepository.save(event);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Event.class));
    }

    @RolesAllowed(Roles.Admin)
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException(id, Event.class);
        }
        eventRepository.deleteById(id);
    }
}

