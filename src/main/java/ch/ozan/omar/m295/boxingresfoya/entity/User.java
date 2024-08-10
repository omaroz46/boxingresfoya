package ch.ozan.omar.m295.boxingresfoya.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * The User entity.
 */
@Data
@Entity
@NamedQuery(
        name = "User.findByName",
        query = "SELECT u FROM User u WHERE u.name = :name"
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false, length = 100)
    private String passwordHash;

    @Column(nullable = false, length = 24)
    private String salt;

    @Column(nullable = false)
    private Date date;

    public User() {}

    public User(String name, String passwordHash, String salt, Date date) {
        this.name = name;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.date = date;
    }
}
