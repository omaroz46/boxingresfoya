package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.service.FighterService;
import ch.ozan.omar.m295.boxingresfoya.entity.Fighter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for managing Fighter entities.
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class FighterController {
    private final FighterService fighterService;

    /**
     * Constructs a new FighterController with the provided FighterService.
     * @param fighterService The service responsible for handling Fighter operations.
     */
    FighterController(FighterService fighterService) {
        this.fighterService = fighterService;
    }

    /**
     * Retrieves all Fighter entities.
     * @return A ResponseEntity containing a list of all Fighter entities.
     */
    @GetMapping("/api/fighters")
    public ResponseEntity<List<Fighter>> all() {
        List<Fighter> fighters = fighterService.getAllFighters();
        return new ResponseEntity<>(fighters, HttpStatus.OK);
    }

    /**
     * Retrieves a single Fighter entity by its ID.
     * @param id The ID of the Fighter entity to retrieve.
     * @return A ResponseEntity containing the requested Fighter entity.
     */
    @GetMapping("/api/fighters/{id}")
    public ResponseEntity<Fighter> one(@PathVariable Long id) {
        Fighter fighter = fighterService.getFighter(id);
        return new ResponseEntity<>(fighter, HttpStatus.OK);
    }

    /**
     * Creates a new Fighter entity.
     * @param fighter The Fighter entity to create.
     * @return A ResponseEntity containing the newly created Fighter entity.
     */
    @PostMapping("/api/fighters")
    public ResponseEntity<Fighter> newFighter(@RequestBody Fighter fighter) {
        Fighter savedFighter = fighterService.createFighter(fighter);
        return new ResponseEntity<>(savedFighter, HttpStatus.CREATED);
    }

    /**
     * Updates an existing Fighter entity.
     * @param fighter The updated Fighter entity.
     * @param id The ID of the Fighter entity to update.
     * @return A ResponseEntity containing the updated Fighter entity.
     */
    @PutMapping("/api/fighters/{id}")
    public ResponseEntity<Fighter> updateFighter(@RequestBody Fighter fighter, @PathVariable Long id) {
        Fighter updatedFighter = fighterService.updateFighter(fighter, id);
        return new ResponseEntity<>(updatedFighter, HttpStatus.OK);
    }

    /**
     * Deletes a Fighter entity by its ID.
     * @param id The ID of the Fighter entity to delete.
     * @return A ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/api/fighters/{id}")
    public ResponseEntity<?> deleteFighter(@PathVariable Long id) {
        fighterService.deleteFighter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

