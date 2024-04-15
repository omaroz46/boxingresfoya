package ch.ozan.omar.m295.boxingresfoya.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

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

    public Fight() {}

    public Fight(LocalDateTime date, String location, String opponent, String result) {
        this.date = date;
        this.location = location;
        this.opponent = opponent;
        this.result = result;
    }
}
