package ch.ozan.omar.m295.boxingresfoya.service;
import ch.ozan.omar.m295.boxingresfoya.entity.Fighter;
import ch.ozan.omar.m295.boxingresfoya.repository.FighterRepository;
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
 * Unit tests for the FighterService class.
 */
public class FighterServiceTest {

    @Mock
    private FighterRepository fighterRepository;

    @InjectMocks
    private FighterService fighterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests the getAllFighters method.
     */
    @Test
    void getAllFightersShouldReturnListOfFighters() {
        List<Fighter> expectedFighters = new ArrayList<>();
        expectedFighters.add(new Fighter());
        expectedFighters.add(new Fighter());
        when(fighterRepository.findAll()).thenReturn(expectedFighters);
        List<Fighter> actualFighters = fighterService.getAllFighters();
        assertEquals(expectedFighters.size(), actualFighters.size());
    }

    /**
     * Tests the getFighter method when the fighter exists.
     */
    @Test
    void getFighterShouldReturnFighterIfExists() {
        Long id = 1L;
        Fighter expectedFighter = new Fighter();
        when(fighterRepository.findById(id)).thenReturn(Optional.of(expectedFighter));
        Fighter actualFighter = fighterService.getFighter(id);
        assertEquals(expectedFighter, actualFighter);
    }

    /**
     * Tests the getFighter method when the fighter does not exist.
     */
    @Test
    void getFighterShouldThrowEntityNotFoundExceptionIfFighterDoesNotExist() {
        Long id = 1L;
        when(fighterRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> fighterService.getFighter(id));
    }

    /**
     * Tests the createFighter method.
     */
    @Test
    void createFighterShouldReturnCreatedFighter() {
        Fighter fighterToCreate = new Fighter();
        when(fighterRepository.save(fighterToCreate)).thenReturn(fighterToCreate);
        Fighter createdFighter = fighterService.createFighter(fighterToCreate);
        assertEquals(fighterToCreate, createdFighter);
    }

    /**
     * Tests the updateFighter method when the fighter exists.
     */
    @Test
    void updateFighterShouldReturnUpdatedFighter() {
        Long id = 1L;
        Fighter existingFighter = new Fighter();
        Fighter updatedFighter = new Fighter();
        when(fighterRepository.findById(id)).thenReturn(Optional.of(existingFighter));
        when(fighterRepository.save(updatedFighter)).thenReturn(updatedFighter);
        Fighter result = fighterService.updateFighter(updatedFighter, id);
        assertEquals(updatedFighter, result);
    }

    /**
     * Tests the updateFighter method when the fighter does not exist.
     */
    @Test
    void updateFighterShouldThrowEntityNotFoundExceptionIfFighterDoesNotExist() {
        Long id = 1L;
        Fighter updatedFighter = new Fighter();
        when(fighterRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> fighterService.updateFighter(updatedFighter, id));
    }

    /**
     * Tests the deleteFighter method when the fighter exists.
     */
    @Test
    void deleteFighterShouldDeleteFighterIfExists() {
        Long id = 1L;
        when(fighterRepository.existsById(id)).thenReturn(true);
        fighterService.deleteFighter(id);
        verify(fighterRepository, times(1)).deleteById(id);
    }

    /**
     * Tests the deleteFighter method when the fighter does not exist.
     */
    @Test
    void deleteFighterShouldThrowEntityNotFoundExceptionIfFighterDoesNotExist() {
        Long id = 1L;
        when(fighterRepository.existsById(id)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> fighterService.deleteFighter(id));
    }
}
