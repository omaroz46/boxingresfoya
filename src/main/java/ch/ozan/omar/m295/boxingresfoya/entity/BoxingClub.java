package ch.ozan.omar.m295.boxingresfoya.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * The BoxingClub entity.
 */
@Data
@Entity
public class BoxingClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(nullable = false, length = 50)
    private String contactInfo;

    /**
     * Default constructor for BoxingClub.
     */
    public BoxingClub() {}

    /**
     * Constructor for BoxingClub with parameters.
     * @param name The name of the BoxingClub.
     * @param location The location of the BoxingClub.
     * @param contactInfo The contact information of the BoxingClub.
     */
    public BoxingClub(String name, String location, String contactInfo) {
        this.name = name;
        this.location = location;
        this.contactInfo = contactInfo;
    }
}
