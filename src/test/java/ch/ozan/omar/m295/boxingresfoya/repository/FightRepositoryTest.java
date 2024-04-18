package ch.ozan.omar.m295.boxingresfoya.repository;

import ch.ozan.omar.m295.boxingresfoya.entity.Fight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FightRepositoryTest {

    @Autowired
    private FightRepository fightRepository;

    @Test
    public void createShouldContainNewlyCreatedFight() {
        // Create
        LocalDateTime date = LocalDateTime.now();
        Fight fight = new Fight(date, "Test Location", "Test Opponent", "Test Result");
        fightRepository.save(fight);

        Optional<Fight> savedFightOptional = fightRepository.findById(fight.getId());
        assertTrue(savedFightOptional.isPresent());
        Fight savedFight = savedFightOptional.get();
        assertEquals(date, savedFight.getDate());
        assertEquals("Test Location", savedFight.getLocation());
        assertEquals("Test Opponent", savedFight.getOpponent());
        assertEquals("Test Result", savedFight.getResult());
    }

    @Test
    public void readShouldContainPreviouslyCreatedFight() {
        // Create
        LocalDateTime date = LocalDateTime.now();
        Fight fight = new Fight(date, "Test Location", "Test Opponent", "Test Result");
        fightRepository.save(fight);

        // Read
        Optional<Fight> savedFightOptional = fightRepository.findById(fight.getId());
        assertTrue(savedFightOptional.isPresent());
        Fight savedFight = savedFightOptional.get();
        assertEquals(date, savedFight.getDate());
        assertEquals("Test Location", savedFight.getLocation());
        assertEquals("Test Opponent", savedFight.getOpponent());
        assertEquals("Test Result", savedFight.getResult());
    }

    @Test
    public void updateShouldContainUpdatedFight() {
        // Create
        LocalDateTime date = LocalDateTime.now();
        Fight fight = new Fight(date, "Test Location", "Test Opponent", "Test Result");
        fightRepository.save(fight);

        // Update
        Optional<Fight> savedFightOptional = fightRepository.findById(fight.getId());
        assertTrue(savedFightOptional.isPresent());
        Fight savedFight = savedFightOptional.get();
        savedFight.setLocation("Updated Location");
        savedFight.setOpponent("Updated Opponent");
        savedFight.setResult("Updated Result");
        fightRepository.save(savedFight);

        Optional<Fight> updatedFightOptional = fightRepository.findById(savedFight.getId());
        assertTrue(updatedFightOptional.isPresent());
        Fight updatedFight = updatedFightOptional.get();
        assertEquals("Updated Location", updatedFight.getLocation());
        assertEquals("Updated Opponent", updatedFight.getOpponent());
        assertEquals("Updated Result", updatedFight.getResult());
    }

    @Test
    public void deleteShouldNotContainDeletedFight() {
        // Create
        LocalDateTime date = LocalDateTime.now();
        Fight fight = new Fight(date, "Test Location", "Test Opponent", "Test Result");
        fightRepository.save(fight);

        // Delete
        fightRepository.delete(fight);
        Optional<Fight> deletedFightOptional = fightRepository.findById(fight.getId());
        assertTrue(deletedFightOptional.isEmpty());
    }
}
