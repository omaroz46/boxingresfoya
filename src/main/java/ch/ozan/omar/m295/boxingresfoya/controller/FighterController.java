package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.entity.Fighter;
import ch.ozan.omar.m295.boxingresfoya.service.FighterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Fighter resources.
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class FighterController {

    private static final Logger logger = LoggerFactory.getLogger(FighterController.class);

    private final FighterService fighterService;

    /**
     * Constructs a FighterController with the specified FighterService.
     *
     * @param fighterService the FighterService to be used
     */
    public FighterController(FighterService fighterService) {
        this.fighterService = fighterService;
    }

    /**
     * Retrieves all fighters.
     *
     * @return a ResponseEntity containing the list of fighters
     */
    @GetMapping("/api/fighter")
    public ResponseEntity<List<Fighter>> all() {
        logger.info("Fetching all fighters");
        List<Fighter> fighters = fighterService.getAllFighters();
        return new ResponseEntity<>(fighters, HttpStatus.OK);
    }

    /**
     * Retrieves a fighter by its ID.
     *
     * @param id the ID of the fighter
     * @return a ResponseEntity containing the fighter
     */
    @GetMapping("/api/fighter/{id}")
    public ResponseEntity<Fighter> one(@PathVariable Long id) {
        logger.info("Fetching fighter with ID: {}", id);
        Fighter fighter = fighterService.getFighter(id);
        return new ResponseEntity<>(fighter, HttpStatus.OK);
    }

    /**
     * Creates a new fighter.
     *
     * @param fighter the fighter to be created
     * @return a ResponseEntity containing the created fighter
     */
    @PostMapping("/api/fighter")
    public ResponseEntity<Fighter> newFighter(@RequestBody Fighter fighter) {
        logger.info("Creating a new fighter: {}", fighter);
        Fighter savedFighter = fighterService.createFighter(fighter);
        return new ResponseEntity<>(savedFighter, HttpStatus.CREATED);
    }

    /**
     * Updates an existing fighter.
     *
     * @param fighter the fighter with updated information
     * @param id the ID of the fighter to be updated
     * @return a ResponseEntity containing the updated fighter
     */
    @PutMapping("/api/fighter/{id}")
    public ResponseEntity<Fighter> updateFighter(@RequestBody Fighter fighter, @PathVariable Long id) {
        logger.info("Updating fighter with ID: {} with data: {}", id, fighter);
        Fighter updatedFighter = fighterService.updateFighter(fighter, id);
        return new ResponseEntity<>(updatedFighter, HttpStatus.OK);
    }

    /**
     * Deletes a fighter by its ID.
     *
     * @param id the ID of the fighter to be deleted
     * @return a ResponseEntity with no content
     */
    @DeleteMapping("/api/fighter/{id}")
    public ResponseEntity<?> deleteFighter(@PathVariable Long id) {
        logger.info("Deleting fighter with ID: {}", id);
        fighterService.deleteFighter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
