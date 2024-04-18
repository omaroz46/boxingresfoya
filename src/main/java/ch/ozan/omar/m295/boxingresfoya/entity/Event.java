package ch.ozan.omar.m295.boxingresfoya.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * The Event entity.
 */
@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String venue;

    @Column(nullable = false)
    private String description;

    /**
     * Default constructor for Event.
     */
    public Event() {}

    /**
     * Constructor for Event with parameters.
     * @param date The date of the Event.
     * @param venue The venue of the Event.
     * @param description The description of the Event.
     */
    public Event(LocalDateTime date, String venue, String description) {
        this.date = date;
        this.venue = venue;
        this.description = description;
    }
}
