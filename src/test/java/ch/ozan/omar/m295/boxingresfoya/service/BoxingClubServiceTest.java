package ch.ozan.omar.m295.boxingresfoya.service;
import ch.ozan.omar.m295.boxingresfoya.entity.BoxingClub;
import ch.ozan.omar.m295.boxingresfoya.repository.BoxingClubRepository;
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

public class BoxingClubServiceTest {

    @Mock
    private BoxingClubRepository boxingClubRepository;

    @InjectMocks
    private BoxingClubService boxingClubService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllBoxingClubsShouldReturnListOfClubs() {
        List<BoxingClub> expectedClubs = new ArrayList<>();
        expectedClubs.add(new BoxingClub());
        expectedClubs.add(new BoxingClub());
        when(boxingClubRepository.findAll()).thenReturn(expectedClubs);
        List<BoxingClub> actualClubs = boxingClubService.getAllBoxingClubs();
        assertEquals(expectedClubs.size(), actualClubs.size());
    }

    @Test
    void getBoxingClubShouldReturnClubIfExists() {
        Long id = 1L;
        BoxingClub expectedClub = new BoxingClub();
        when(boxingClubRepository.findById(id)).thenReturn(Optional.of(expectedClub));
        BoxingClub actualClub = boxingClubService.getBoxingClub(id);
        assertEquals(expectedClub, actualClub);
    }

    @Test
    void getBoxingClubShouldThrowEntityNotFoundExceptionIfClubDoesNotExist() {
        Long id = 1L;
        when(boxingClubRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> boxingClubService.getBoxingClub(id));
    }

    @Test
    void createBoxingClubShouldReturnCreatedClub() {
        BoxingClub clubToCreate = new BoxingClub();
        when(boxingClubRepository.save(clubToCreate)).thenReturn(clubToCreate);
        BoxingClub createdClub = boxingClubService.createBoxingClub(clubToCreate);
        assertEquals(clubToCreate, createdClub);
    }

    @Test
    void updateBoxingClubShouldReturnUpdatedClub() {
        Long id = 1L;
        BoxingClub existingClub = new BoxingClub();
        BoxingClub updatedClub = new BoxingClub();
        when(boxingClubRepository.findById(id)).thenReturn(Optional.of(existingClub));
        when(boxingClubRepository.save(updatedClub)).thenReturn(updatedClub);
        BoxingClub result = boxingClubService.updateBoxingClub(updatedClub, id);
        assertEquals(updatedClub, result);
    }

    @Test
    void updateBoxingClubShouldThrowEntityNotFoundExceptionIfClubDoesNotExist() {
        Long id = 1L;
        BoxingClub updatedClub = new BoxingClub();
        when(boxingClubRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> boxingClubService.updateBoxingClub(updatedClub, id));
    }

    @Test
    void deleteBoxingClubShouldDeleteClubIfExists() {
        Long id = 1L;
        when(boxingClubRepository.existsById(id)).thenReturn(true);
        boxingClubService.deleteBoxingClub(id);
        verify(boxingClubRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteBoxingClubShouldThrowEntityNotFoundExceptionIfClubDoesNotExist() {
        Long id = 1L;
        when(boxingClubRepository.existsById(id)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> boxingClubService.deleteBoxingClub(id));
    }
}

