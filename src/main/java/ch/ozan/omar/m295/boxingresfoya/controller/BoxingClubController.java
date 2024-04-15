package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.service.BoxingClubService;
import ch.ozan.omar.m295.boxingresfoya.entity.BoxingClub;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class BoxingClubController {
    private final BoxingClubService boxingClubService;

    BoxingClubController(BoxingClubService boxingClubService) {
        this.boxingClubService = boxingClubService;
    }

    @GetMapping("/api/boxingclub")
    public ResponseEntity<List<BoxingClub>> all() {
        List<BoxingClub> boxingClubs = boxingClubService.getAllBoxingClubs();
        return new ResponseEntity<>(boxingClubs, HttpStatus.OK);
    }

    @GetMapping("/api/boxingclub/{id}")
    public ResponseEntity<BoxingClub> one(@PathVariable Long id) {
        BoxingClub boxingClub = boxingClubService.getBoxingClub(id);
        return new ResponseEntity<>(boxingClub, HttpStatus.OK);
    }

    @PostMapping("/api/boxingclub")
    public ResponseEntity<BoxingClub> newBoxingClub(@RequestBody BoxingClub boxingClub) {
        BoxingClub savedBoxingClub = boxingClubService.createBoxingClub(boxingClub);
        return new ResponseEntity<>(savedBoxingClub, HttpStatus.CREATED);
    }

    @PutMapping("/api/boxingclub/{id}")
    public ResponseEntity<BoxingClub> updateBoxingClub(@RequestBody BoxingClub boxingClub, @PathVariable Long id) {
        BoxingClub updatedBoxingClub = boxingClubService.updateBoxingClub(boxingClub, id);
        return new ResponseEntity<>(updatedBoxingClub, HttpStatus.OK);
    }

    @DeleteMapping("/api/boxingclub/{id}")
    public ResponseEntity<?> deleteBoxingClub(@PathVariable Long id) {
        boxingClubService.deleteBoxingClub(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
