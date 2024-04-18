package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.service.BoxingClubService;
import ch.ozan.omar.m295.boxingresfoya.entity.BoxingClub;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for managing BoxingClub entities.
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class BoxingClubController {
    private final BoxingClubService boxingClubService;

    /**
     * Constructs a new BoxingClubController with the provided BoxingClubService.
     * @param boxingClubService The service responsible for handling BoxingClub operations.
     */
    BoxingClubController(BoxingClubService boxingClubService) {
        this.boxingClubService = boxingClubService;
    }

    /**
     * Retrieves all BoxingClub entities.
     * @return A ResponseEntity containing a list of all BoxingClub entities.
     */
    @GetMapping("/api/boxingclub")
    public ResponseEntity<List<BoxingClub>> all() {
        List<BoxingClub> boxingClubs = boxingClubService.getAllBoxingClubs();
        return new ResponseEntity<>(boxingClubs, HttpStatus.OK);
    }

    /**
     * Retrieves a single BoxingClub entity by its ID.
     * @param id The ID of the BoxingClub entity to retrieve.
     * @return A ResponseEntity containing the requested BoxingClub entity.
     */
    @GetMapping("/api/boxingclub/{id}")
    public ResponseEntity<BoxingClub> one(@PathVariable Long id) {
        BoxingClub boxingClub = boxingClubService.getBoxingClub(id);
        return new ResponseEntity<>(boxingClub, HttpStatus.OK);
    }

    /**
     * Creates a new BoxingClub entity.
     * @param boxingClub The BoxingClub entity to create.
     * @return A ResponseEntity containing the newly created BoxingClub entity.
     */
    @PostMapping("/api/boxingclub")
    public ResponseEntity<BoxingClub> newBoxingClub(@RequestBody BoxingClub boxingClub) {
        BoxingClub savedBoxingClub = boxingClubService.createBoxingClub(boxingClub);
        return new ResponseEntity<>(savedBoxingClub, HttpStatus.CREATED);
    }

    /**
     * Updates an existing BoxingClub entity.
     * @param boxingClub The updated BoxingClub entity.
     * @param id The ID of the BoxingClub entity to update.
     * @return A ResponseEntity containing the updated BoxingClub entity.
     */

    @PutMapping("/api/boxingclub/{id}")
    public ResponseEntity<BoxingClub> updateBoxingClub(@RequestBody BoxingClub boxingClub, @PathVariable Long id) {
        BoxingClub updatedBoxingClub = boxingClubService.updateBoxingClub(boxingClub, id);
        return new ResponseEntity<>(updatedBoxingClub, HttpStatus.OK);
    }

    /**
     * Deletes a BoxingClub entity by its ID.
     * @param id The ID of the BoxingClub entity to delete.
     * @return A ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/api/boxingclub/{id}")
    public ResponseEntity<?> deleteBoxingClub(@PathVariable Long id) {
        boxingClubService.deleteBoxingClub(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
