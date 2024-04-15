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

public class FighterServiceTest {

    @Mock
    private FighterRepository fighterRepository;

    @InjectMocks
    private FighterService fighterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllFightersShouldReturnListOfFighters() {
        List<Fighter> expectedFighters = new ArrayList<>();
        expectedFighters.add(new Fighter());
        expectedFighters.add(new Fighter());
        when(fighterRepository.findAll()).thenReturn(expectedFighters);
        List<Fighter> actualFighters = fighterService.getAllFighters();
        assertEquals(expectedFighters.size(), actualFighters.size());
    }

    @Test
    void getFighterShouldReturnFighterIfExists() {
        Long id = 1L;
        Fighter expectedFighter = new Fighter();
        when(fighterRepository.findById(id)).thenReturn(Optional.of(expectedFighter));
        Fighter actualFighter = fighterService.getFighter(id);
        assertEquals(expectedFighter, actualFighter);
    }

    @Test
    void getFighterShouldThrowEntityNotFoundExceptionIfFighterDoesNotExist() {
        Long id = 1L;
        when(fighterRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> fighterService.getFighter(id));
    }

    @Test
    void createFighterShouldReturnCreatedFighter() {
        Fighter fighterToCreate = new Fighter();
        when(fighterRepository.save(fighterToCreate)).thenReturn(fighterToCreate);
        Fighter createdFighter = fighterService.createFighter(fighterToCreate);
        assertEquals(fighterToCreate, createdFighter);
    }

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

    @Test
    void updateFighterShouldThrowEntityNotFoundExceptionIfFighterDoesNotExist() {
        Long id = 1L;
        Fighter updatedFighter = new Fighter();
        when(fighterRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> fighterService.updateFighter(updatedFighter, id));
    }

    @Test
    void deleteFighterShouldDeleteFighterIfExists() {
        Long id = 1L;
        when(fighterRepository.existsById(id)).thenReturn(true);
        fighterService.deleteFighter(id);
        verify(fighterRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteFighterShouldThrowEntityNotFoundExceptionIfFighterDoesNotExist() {
        Long id = 1L;
        when(fighterRepository.existsById(id)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> fighterService.deleteFighter(id));
    }
}
