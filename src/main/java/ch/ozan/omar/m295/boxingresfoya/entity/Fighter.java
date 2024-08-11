package ch.ozan.omar.m295.boxingresfoya.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * The Fighter entity.
 */
@Data
@Entity
@NamedQuery(
        name = "Fighter.findName",
        query = "SELECT f FROM Fighter f WHERE f.name = :name"
)
public class Fighter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 30)
    private String weightClass;

    @Column(nullable = false, length = 15)
    private String fightRecord;

    @ManyToOne
    @JoinColumn(name = "boxing_club_id")
    private BoxingClub boxingClub;

    public Fighter() {}

    public Fighter(String name, String weightClass, String fightRecord) {
        this.name = name;
        this.weightClass = weightClass;
        this.fightRecord = fightRecord;
    }
}
