package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.entity.BoxingClub;
import ch.ozan.omar.m295.boxingresfoya.service.BoxingClubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the BoxingClubController class.
 */
public class BoxingClubControllerTest {

    @Mock
    private BoxingClubService boxingClubService;

    @InjectMocks
    private BoxingClubController boxingClubController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test case for retrieving all boxing clubs.
     */
    @Test
    void testGetAllBoxingClubs() {
        List<BoxingClub> expectedBoxingClubs = new ArrayList<>();
        expectedBoxingClubs.add(new BoxingClub());
        expectedBoxingClubs.add(new BoxingClub());
        when(boxingClubService.getAllBoxingClubs()).thenReturn(expectedBoxingClubs);
        ResponseEntity<List<BoxingClub>> responseEntity = boxingClubController.all();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedBoxingClubs.size(), responseEntity.getBody().size());
    }

    /**
     * Test case for retrieving a boxing club by ID.
     */
    @Test
    void testGetBoxingClubById() {
        Long id = 1L;
        BoxingClub expectedBoxingClub = new BoxingClub();
        when(boxingClubService.getBoxingClub(id)).thenReturn(expectedBoxingClub);
        ResponseEntity<BoxingClub> responseEntity = boxingClubController.one(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedBoxingClub, responseEntity.getBody());
    }

    /**
     * Test case for creating a new boxing club.
     */
    @Test
    void testCreateBoxingClub() {
        BoxingClub boxingClub = new BoxingClub();
        when(boxingClubService.createBoxingClub(boxingClub)).thenReturn(boxingClub);
        ResponseEntity<BoxingClub> responseEntity = boxingClubController.newBoxingClub(boxingClub);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(boxingClub, responseEntity.getBody());
    }

    /**
     * Test case for updating an existing boxing club.
     */
    @Test
    void testUpdateBoxingClub() {
        Long id = 1L;
        BoxingClub boxingClub = new BoxingClub();
        when(boxingClubService.updateBoxingClub(boxingClub, id)).thenReturn(boxingClub);
        ResponseEntity<BoxingClub> responseEntity = boxingClubController.updateBoxingClub(boxingClub, id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(boxingClub, responseEntity.getBody());
    }

    /**
     * Test case for deleting a boxing club.
     */
    @Test
    void testDeleteBoxingClub() {
        Long id = 1L;
        ResponseEntity<?> responseEntity = boxingClubController.deleteBoxingClub(id);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(boxingClubService, times(1)).deleteBoxingClub(id);
    }
}

