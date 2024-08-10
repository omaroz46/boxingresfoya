package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.entity.Fight;
import ch.ozan.omar.m295.boxingresfoya.service.FightService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Fight resources.
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class FightController {

    private static final Logger logger = LoggerFactory.getLogger(FightController.class);

    private final FightService fightService;

    /**
     * Constructs a FightController with the specified FightService.
     *
     * @param fightService the FightService to be used
     */
    public FightController(FightService fightService) {
        this.fightService = fightService;
    }

    /**
     * Retrieves all fights.
     *
     * @return a ResponseEntity containing the list of fights
     */
    @GetMapping("/api/fight")
    public ResponseEntity<List<Fight>> all() {
        logger.info("Fetching all fights");
        List<Fight> fights = fightService.getAllFights();
        return new ResponseEntity<>(fights, HttpStatus.OK);
    }

    /**
     * Retrieves a fight by its ID.
     *
     * @param id the ID of the fight
     * @return a ResponseEntity containing the fight
     */
    @GetMapping("/api/fight/{id}")
    public ResponseEntity<Fight> one(@PathVariable Long id) {
        logger.info("Fetching fight with ID: {}", id);
        Fight fight = fightService.getFight(id);
        return new ResponseEntity<>(fight, HttpStatus.OK);
    }

    /**
     * Creates a new fight.
     *
     * @param fight the fight to be created
     * @return a ResponseEntity containing the created fight
     */
    @PostMapping("/api/fight")
    public ResponseEntity<Fight> newFight(@RequestBody Fight fight) {
        logger.info("Creating a new fight: {}", fight);
        Fight savedFight = fightService.createFight(fight);
        return new ResponseEntity<>(savedFight, HttpStatus.CREATED);
    }

    /**
     * Updates an existing fight.
     *
     * @param fight the fight with updated information
     * @param id the ID of the fight to be updated
     * @return a ResponseEntity containing the updated fight
     */
    @PutMapping("/api/fight/{id}")
    public ResponseEntity<Fight> updateFight(@RequestBody Fight fight, @PathVariable Long id) {
        logger.info("Updating fight with ID: {} with data: {}", id, fight);
        Fight updatedFight = fightService.updateFight(fight, id);
        return new ResponseEntity<>(updatedFight, HttpStatus.OK);
    }

    /**
     * Deletes a fight by its ID.
     *
     * @param id the ID of the fight to be deleted
     * @return a ResponseEntity with no content
     */
    @DeleteMapping("/api/fight/{id}")
    public ResponseEntity<?> deleteFight(@PathVariable Long id) {
        logger.info("Deleting fight with ID: {}", id);
        fightService.deleteFight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
