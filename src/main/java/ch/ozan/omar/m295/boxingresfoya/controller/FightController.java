package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.entity.Fight;
import ch.ozan.omar.m295.boxingresfoya.service.FightService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for managing Fight entities.
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class FightController {
    private final FightService fightService;

    /**
     * Constructs a new FightController with the provided FightService.
     * @param fightService The service responsible for handling Fight operations.
     */
    FightController(FightService fightService) {
        this.fightService = fightService;
    }

    /**
     * Retrieves all Fight entities.
     * @return A ResponseEntity containing a list of all Fight entities.
     */
    @GetMapping("/api/fights")
    public ResponseEntity<List<Fight>> all() {
        List<Fight> fights = fightService.getAllFights();
        return new ResponseEntity<>(fights, HttpStatus.OK);
    }

    /**
     * Retrieves a single Fight entity by its ID.
     * @param id The ID of the Fight entity to retrieve.
     * @return A ResponseEntity containing the requested Fight entity.
     */
    @GetMapping("/api/fights/{id}")
    public ResponseEntity<Fight> one(@PathVariable Long id) {
        Fight fight = fightService.getFight(id);
        return new ResponseEntity<>(fight, HttpStatus.OK);
    }

    /**
     * Creates a new Fight entity.
     * @param fight The Fight entity to create.
     * @return A ResponseEntity containing the newly created Fight entity.
     */
    @PostMapping("/api/fights")
    public ResponseEntity<Fight> newFight(@RequestBody Fight fight) {
        Fight savedFight = fightService.createFight(fight);
        return new ResponseEntity<>(savedFight, HttpStatus.CREATED);
    }

    /**
     * Updates an existing Fight entity.
     * @param fight The updated Fight entity.
     * @param id The ID of the Fight entity to update.
     * @return A ResponseEntity containing the updated Fight entity.
     */
    @PutMapping("/api/fights/{id}")
    public ResponseEntity<Fight> updateFight(@RequestBody Fight fight, @PathVariable Long id) {
        Fight updatedFight = fightService.updateFight(fight, id);
        return new ResponseEntity<>(updatedFight, HttpStatus.OK);
    }

    /**
     * Deletes a Fight entity by its ID.
     * @param id The ID of the Fight entity to delete.
     * @return A ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/api/fights/{id}")
    public ResponseEntity<?> deleteFight(@PathVariable Long id) {
        fightService.deleteFight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

