package ch.ozan.omar.m295.boxingresfoya.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * The Event entity.
 */
@Data
@Entity
@NamedQuery(
        name = "Event.findByVenue",
        query = "SELECT e FROM Event e WHERE e.venue = :venue"
)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false, length = 100)
    private String venue;

    @Column(nullable = false)
    private String description;

    public Event() {}

    public Event(LocalDateTime date, String venue, String description) {
        this.date = date;
        this.venue = venue;
        this.description = description;
    }
}
