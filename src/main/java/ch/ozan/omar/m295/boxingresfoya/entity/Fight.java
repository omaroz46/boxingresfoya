package ch.ozan.omar.m295.boxingresfoya.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * The Fight entity.
 */
@Data
@Entity
public class Fight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String opponent;

    @Column(nullable = false)
    private String result;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    /**
     * Default constructor for Fight.
     */
    public Fight() {}

    /**
     * Constructor for Fight with parameters.
     * @param date The date of the Fight.
     * @param location The location of the Fight.
     * @param opponent The opponent of the Fight.
     * @param result The result of the Fight.
     */
    public Fight(LocalDateTime date, String location, String opponent, String result) {
        this.date = date;
        this.location = location;
        this.opponent = opponent;
        this.result = result;
    }
}
