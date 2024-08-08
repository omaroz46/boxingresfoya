package ch.ozan.omar.m295.boxingresfoya.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * The Fighter entity.
 */
@Data
@Entity
@NamedQuery(
        name = "Fighter.findByFirstName",
        query = "SELECT f FROM Fighter f WHERE f.firstName = :firstName"
)
@NamedQuery(
        name = "Fighter.findByLastName",
        query = "SELECT f FROM Fighter f WHERE f.lastName = :lastName"
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

    /**
     * Default constructor for Fighter.
     */
    public Fighter() {}

    /**
     * Constructor for Fighter with parameters.
     * @param name The name of the Fighter.
     * @param weightClass The weight class of the Fighter.
     * @param fightRecord The fight record of the Fighter.
     */
    public Fighter(String name, String weightClass, String fightRecord) {
        this.name = name;
        this.weightClass = weightClass;
        this.fightRecord = fightRecord;
    }
}