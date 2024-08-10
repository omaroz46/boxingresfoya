package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.entity.BoxingClub;
import ch.ozan.omar.m295.boxingresfoya.service.BoxingClubService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Boxing Club resources.
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class BoxingClubController {

    private static final Logger logger = LoggerFactory.getLogger(BoxingClubController.class);

    private final BoxingClubService boxingClubService;

    /**
     * Constructs a BoxingClubController with the specified BoxingClubService.
     *
     * @param boxingClubService the BoxingClubService to be used
     */
    public BoxingClubController(BoxingClubService boxingClubService) {
        this.boxingClubService = boxingClubService;
    }

    /**
     * Retrieves all boxing clubs.
     *
     * @return a ResponseEntity containing the list of boxing clubs
     */
    @GetMapping("/api/boxingclub")
    public ResponseEntity<List<BoxingClub>> all() {
        logger.info("Fetching all boxing clubs");
        List<BoxingClub> boxingClubs = boxingClubService.getAllBoxingClubs();
        return new ResponseEntity<>(boxingClubs, HttpStatus.OK);
    }

    /**
     * Retrieves a boxing club by its ID.
     *
     * @param id the ID of the boxing club
     * @return a ResponseEntity containing the boxing club
     */
    @GetMapping("/api/boxingclub/{id}")
    public ResponseEntity<BoxingClub> one(@PathVariable Long id) {
        logger.info("Fetching boxing club with ID: {}", id);
        BoxingClub boxingClub = boxingClubService.getBoxingClub(id);
        return new ResponseEntity<>(boxingClub, HttpStatus.OK);
    }

    /**
     * Creates a new boxing club.
     *
     * @param boxingClub the boxing club to be created
     * @return a ResponseEntity containing the created boxing club
     */
    @PostMapping("/api/boxingclub")
    public ResponseEntity<BoxingClub> newBoxingClub(@RequestBody BoxingClub boxingClub) {
        logger.info("Creating a new boxing club: {}", boxingClub);
        BoxingClub savedBoxingClub = boxingClubService.createBoxingClub(boxingClub);
        return new ResponseEntity<>(savedBoxingClub, HttpStatus.CREATED);
    }

    /**
     * Updates an existing boxing club.
     *
     * @param boxingClub the boxing club with updated information
     * @param id the ID of the boxing club to be updated
     * @return a ResponseEntity containing the updated boxing club
     */
    @PutMapping("/api/boxingclub/{id}")
    public ResponseEntity<BoxingClub> updateBoxingClub(@RequestBody BoxingClub boxingClub, @PathVariable Long id) {
        logger.info("Updating boxing club with ID: {} with data: {}", id, boxingClub);
        BoxingClub updatedBoxingClub = boxingClubService.updateBoxingClub(boxingClub, id);
        return new ResponseEntity<>(updatedBoxingClub, HttpStatus.OK);
    }

    /**
     * Deletes a boxing club by its ID.
     *
     * @param id the ID of the boxing club to be deleted
     * @return a ResponseEntity with no content
     */
    @DeleteMapping("/api/boxingclub/{id}")
    public ResponseEntity<?> deleteBoxingClub(@PathVariable Long id) {
        logger.info("Deleting boxing club with ID: {}", id);
        boxingClubService.deleteBoxingClub(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
