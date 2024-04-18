package ch.ozan.omar.m295.boxingresfoya.service;
import ch.ozan.omar.m295.boxingresfoya.entity.Fight;
import ch.ozan.omar.m295.boxingresfoya.repository.FightRepository;
import ch.ozan.omar.m295.boxingresfoya.service.FightService;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the FightService class.
 */
public class FightServiceTest {

    @Mock
    private FightRepository fightRepository;

    @InjectMocks
    private FightService fightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests the getAllFights method.
     */
    @Test
    void getAllFightsShouldReturnListOfFights() {
        List<Fight> expectedFights = new ArrayList<>();
        expectedFights.add(new Fight());
        expectedFights.add(new Fight());
        when(fightRepository.findAll()).thenReturn(expectedFights);
        List<Fight> actualFights = fightService.getAllFights();
        assertEquals(expectedFights.size(), actualFights.size());
    }

    /**
     * Tests the getFight method when the fight exists.
     */
    @Test
    void getFightShouldReturnFightIfExists() {
        Long id = 1L;
        Fight expectedFight = new Fight();
        when(fightRepository.findById(id)).thenReturn(Optional.of(expectedFight));
        Fight actualFight = fightService.getFight(id);
        assertEquals(expectedFight, actualFight);
    }

    /**
     * Tests the getFight method when the fight does not exist.
     */
    @Test
    void getFightShouldThrowEntityNotFoundExceptionIfFightDoesNotExist() {
        Long id = 1L;
        when(fightRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> fightService.getFight(id));
    }

    /**
     * Tests the createFight method.
     */
    @Test
    void createFightShouldReturnCreatedFight() {
        Fight fightToCreate = new Fight();
        when(fightRepository.save(fightToCreate)).thenReturn(fightToCreate);
        Fight createdFight = fightService.createFight(fightToCreate);
        assertEquals(fightToCreate, createdFight);
    }

    /**
     * Tests the updateFight method when the fight exists.
     */
    @Test
    void updateFightShouldReturnUpdatedFight() {
        Long id = 1L;
        Fight existingFight = new Fight();
        Fight updatedFight = new Fight();
        when(fightRepository.findById(id)).thenReturn(Optional.of(existingFight));
        when(fightRepository.save(updatedFight)).thenReturn(updatedFight);
        Fight result = fightService.updateFight(updatedFight, id);
        assertEquals(updatedFight, result);
    }

    /**
     * Tests the updateFight method when the fight does not exist.
     */
    @Test
    void updateFightShouldThrowEntityNotFoundExceptionIfFightDoesNotExist() {
        Long id = 1L;
        Fight updatedFight = new Fight();
        when(fightRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> fightService.updateFight(updatedFight, id));
    }

    /**
     * Tests the deleteFight method when the fight exists.
     */
    @Test
    void deleteFightShouldDeleteFightIfExists() {
        Long id = 1L;
        when(fightRepository.existsById(id)).thenReturn(true);
        fightService.deleteFight(id);
        verify(fightRepository, times(1)).deleteById(id);
    }

    /**
     * Tests the deleteFight method when the fight does not exist.
     */
    @Test
    void deleteFightShouldThrowEntityNotFoundExceptionIfFightDoesNotExist() {
        Long id = 1L;
        when(fightRepository.existsById(id)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> fightService.deleteFight(id));
    }
}
