package ch.ozan.omar.m295.boxingresfoya.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * The Fighter entity.
 */
@Data
@Entity
public class Fighter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "weight_class", nullable = false)
    private String weightClass;

    @Column(name = "fight_record", nullable = false)
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