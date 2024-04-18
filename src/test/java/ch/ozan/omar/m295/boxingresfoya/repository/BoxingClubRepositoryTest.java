package ch.ozan.omar.m295.boxingresfoya.repository;

import ch.ozan.omar.m295.boxingresfoya.entity.BoxingClub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the BoxingClubRepository class.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BoxingClubRepositoryTest {

    @Autowired
    private BoxingClubRepository boxingClubRepository;

    /**
     * Tests creating a new boxing club.
     */
    @Test
    public void createShouldContainNewlyCreatedBoxingClub() {
        // Create
        BoxingClub boxingClub = new BoxingClub("Test Boxing Club", "Test Location", "Test Contact Info");
        boxingClubRepository.save(boxingClub);

        Optional<BoxingClub> savedBoxingClubOptional = boxingClubRepository.findById(boxingClub.getId());
        assertTrue(savedBoxingClubOptional.isPresent());
        BoxingClub savedBoxingClub = savedBoxingClubOptional.get();
        assertEquals("Test Boxing Club", savedBoxingClub.getName());
        assertEquals("Test Location", savedBoxingClub.getLocation());
        assertEquals("Test Contact Info", savedBoxingClub.getContactInfo());
    }

    /**
     * Tests reading a previously created boxing club.
     */
    @Test
    public void readShouldContainPreviouslyCreatedBoxingClub() {
        // Create
        BoxingClub boxingClub = new BoxingClub("Test Boxing Club", "Test Location", "Test Contact Info");
        boxingClubRepository.save(boxingClub);

        // Read
        Optional<BoxingClub> savedBoxingClubOptional = boxingClubRepository.findById(boxingClub.getId());
        assertTrue(savedBoxingClubOptional.isPresent());
        BoxingClub savedBoxingClub = savedBoxingClubOptional.get();
        assertEquals("Test Boxing Club", savedBoxingClub.getName());
        assertEquals("Test Location", savedBoxingClub.getLocation());
        assertEquals("Test Contact Info", savedBoxingClub.getContactInfo());
    }

    /**
     * Tests updating an existing boxing club.
     */
    @Test
    public void updateShouldContainUpdatedBoxingClub() {
        // Create
        BoxingClub boxingClub = new BoxingClub("Test Boxing Club", "Test Location", "Test Contact Info");
        boxingClubRepository.save(boxingClub);

        // Update
        Optional<BoxingClub> savedBoxingClubOptional = boxingClubRepository.findById(boxingClub.getId());
        assertTrue(savedBoxingClubOptional.isPresent());
        BoxingClub savedBoxingClub = savedBoxingClubOptional.get();
        savedBoxingClub.setLocation("Updated Location");
        savedBoxingClub.setContactInfo("Updated Contact Info");
        boxingClubRepository.save(savedBoxingClub);

        Optional<BoxingClub> updatedBoxingClubOptional = boxingClubRepository.findById(savedBoxingClub.getId());
        assertTrue(updatedBoxingClubOptional.isPresent());
        BoxingClub updatedBoxingClub = updatedBoxingClubOptional.get();
        assertEquals("Updated Location", updatedBoxingClub.getLocation());
        assertEquals("Updated Contact Info", updatedBoxingClub.getContactInfo());
    }

    /**
     * Tests deleting an existing boxing club.
     */
    @Test
    public void deleteShouldNotContainDeletedBoxingClub() {
        // Create
        BoxingClub boxingClub = new BoxingClub("Test Boxing Club", "Test Location", "Test Contact Info");
        boxingClubRepository.save(boxingClub);

        // Delete
        boxingClubRepository.delete(boxingClub);
        Optional<BoxingClub> deletedBoxingClubOptional = boxingClubRepository.findById(boxingClub.getId());
        assertTrue(deletedBoxingClubOptional.isEmpty());
    }
}
