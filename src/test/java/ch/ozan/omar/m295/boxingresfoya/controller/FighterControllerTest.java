package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.entity.Fighter;
import ch.ozan.omar.m295.boxingresfoya.service.FighterService;
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

public class FighterControllerTest {

    @Mock
    private FighterService fighterService;

    @InjectMocks
    private FighterController fighterController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllFighters() {
        List<Fighter> expectedFighters = new ArrayList<>();
        expectedFighters.add(new Fighter());
        expectedFighters.add(new Fighter());
        when(fighterService.getAllFighters()).thenReturn(expectedFighters);
        ResponseEntity<List<Fighter>> responseEntity = fighterController.all();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedFighters.size(), responseEntity.getBody().size());
    }

    @Test
    void testGetFighterById() {
        Long id = 1L;
        Fighter expectedFighter = new Fighter();
        when(fighterService.getFighter(id)).thenReturn(expectedFighter);
        ResponseEntity<Fighter> responseEntity = fighterController.one(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedFighter, responseEntity.getBody());
    }

    @Test
    void testCreateFighter() {
        Fighter fighter = new Fighter();
        when(fighterService.createFighter(fighter)).thenReturn(fighter);
        ResponseEntity<Fighter> responseEntity = fighterController.newFighter(fighter);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(fighter, responseEntity.getBody());
    }

    @Test
    void testUpdateFighter() {
        Long id = 1L;
        Fighter fighter = new Fighter();
        when(fighterService.updateFighter(fighter, id)).thenReturn(fighter);
        ResponseEntity<Fighter> responseEntity = fighterController.updateFighter(fighter, id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(fighter, responseEntity.getBody());
    }

    @Test
    void testDeleteFighter() {
        Long id = 1L;
        ResponseEntity<?> responseEntity = fighterController.deleteFighter(id);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(fighterService, times(1)).deleteFighter(id);
    }
}

