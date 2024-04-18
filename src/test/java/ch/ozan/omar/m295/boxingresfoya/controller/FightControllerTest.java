package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.entity.Fight;
import ch.ozan.omar.m295.boxingresfoya.service.FightService;
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
 * Unit tests for the FightController class.
 */
public class FightControllerTest {

    @Mock
    private FightService fightService;

    @InjectMocks
    private FightController fightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests the all method of FightController.
     */
    @Test
    void testGetAllFights() {
        List<Fight> expectedFights = new ArrayList<>();
        expectedFights.add(new Fight());
        expectedFights.add(new Fight());
        when(fightService.getAllFights()).thenReturn(expectedFights);
        ResponseEntity<List<Fight>> responseEntity = fightController.all();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedFights.size(), responseEntity.getBody().size());
    }

    /**
     * Tests the one method of FightController.
     */
    @Test
    void testGetFightById() {
        Long id = 1L;
        Fight expectedFight = new Fight();
        when(fightService.getFight(id)).thenReturn(expectedFight);
        ResponseEntity<Fight> responseEntity = fightController.one(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedFight, responseEntity.getBody());
    }

    /**
     * Tests the newFight method of FightController.
     */
    @Test
    void testCreateFight() {
        Fight fight = new Fight();
        when(fightService.createFight(fight)).thenReturn(fight);
        ResponseEntity<Fight> responseEntity = fightController.newFight(fight);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(fight, responseEntity.getBody());
    }

    /**
     * Tests the updateFight method of FightController.
     */
    @Test
    void testUpdateFight() {
        Long id = 1L;
        Fight fight = new Fight();
        when(fightService.updateFight(fight, id)).thenReturn(fight);
        ResponseEntity<Fight> responseEntity = fightController.updateFight(fight, id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(fight, responseEntity.getBody());
    }

    /**
     * Tests the deleteFight method of FightController.
     */
    @Test
    void testDeleteFight() {
        Long id = 1L;
        ResponseEntity<?> responseEntity = fightController.deleteFight(id);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(fightService, times(1)).deleteFight(id);
    }
}

