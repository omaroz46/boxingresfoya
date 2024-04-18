package ch.ozan.omar.m295.boxingresfoya.repository;

import ch.ozan.omar.m295.boxingresfoya.entity.Fighter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FighterRepositoryTest {

    @Autowired
    private FighterRepository fighterRepository;

    @Test
    public void createShouldContainNewlyCreatedFighter() {
        // Create
        Fighter fighter = new Fighter("Test Fighter", "Test Weight Class", "Test Fight Record");
        fighterRepository.save(fighter);

        Optional<Fighter> savedFighterOptional = fighterRepository.findById(fighter.getId());
        assertTrue(savedFighterOptional.isPresent());
        Fighter savedFighter = savedFighterOptional.get();
        assertEquals("Test Fighter", savedFighter.getName());
        assertEquals("Test Weight Class", savedFighter.getWeightClass());
        assertEquals("Test Fight Record", savedFighter.getFightRecord());
    }

    @Test
    public void readShouldContainPreviouslyCreatedFighter() {
        // Create
        Fighter fighter = new Fighter("Test Fighter", "Test Weight Class", "Test Fight Record");
        fighterRepository.save(fighter);

        // Read
        Optional<Fighter> savedFighterOptional = fighterRepository.findById(fighter.getId());
        assertTrue(savedFighterOptional.isPresent());
        Fighter savedFighter = savedFighterOptional.get();
        assertEquals("Test Fighter", savedFighter.getName());
        assertEquals("Test Weight Class", savedFighter.getWeightClass());
        assertEquals("Test Fight Record", savedFighter.getFightRecord());
    }

    @Test
    public void updateShouldContainUpdatedFighter() {
        // Create
        Fighter fighter = new Fighter("Test Fighter", "Test Weight Class", "Test Fight Record");
        fighterRepository.save(fighter);

        // Update
        Optional<Fighter> savedFighterOptional = fighterRepository.findById(fighter.getId());
        assertTrue(savedFighterOptional.isPresent());
        Fighter savedFighter = savedFighterOptional.get();
        savedFighter.setWeightClass("Updated Weight Class");
        savedFighter.setFightRecord("Updated Fight Record");
        fighterRepository.save(savedFighter);

        Optional<Fighter> updatedFighterOptional = fighterRepository.findById(savedFighter.getId());
        assertTrue(updatedFighterOptional.isPresent());
        Fighter updatedFighter = updatedFighterOptional.get();
        assertEquals("Updated Weight Class", updatedFighter.getWeightClass());
        assertEquals("Updated Fight Record", updatedFighter.getFightRecord());
    }

    @Test
    public void deleteShouldNotContainDeletedFighter() {
        // Create
        Fighter fighter = new Fighter("Test Fighter", "Test Weight Class", "Test Fight Record");
        fighterRepository.save(fighter);

        // Delete
        fighterRepository.delete(fighter);
        Optional<Fighter> deletedFighterOptional = fighterRepository.findById(fighter.getId());
        assertTrue(deletedFighterOptional.isEmpty());
    }
}
