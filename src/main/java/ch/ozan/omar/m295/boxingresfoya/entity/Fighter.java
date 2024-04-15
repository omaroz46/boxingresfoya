package ch.ozan.omar.m295.boxingresfoya.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    public Fighter() {}

    public Fighter(String name, String weightClass, String fightRecord) {
        this.name = name;
        this.weightClass = weightClass;
        this.fightRecord = fightRecord;
    }
}