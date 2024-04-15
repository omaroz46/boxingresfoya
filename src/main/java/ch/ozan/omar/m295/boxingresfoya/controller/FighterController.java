package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.service.FighterService;
import ch.ozan.omar.m295.boxingresfoya.entity.Fighter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class FighterController {
    private final FighterService fighterService;

    FighterController(FighterService fighterService) {
        this.fighterService = fighterService;
    }

    @GetMapping("/api/fighters")
    public ResponseEntity<List<Fighter>> all() {
        List<Fighter> fighters = fighterService.getAllFighters();
        return new ResponseEntity<>(fighters, HttpStatus.OK);
    }

    @GetMapping("/api/fighters/{id}")
    public ResponseEntity<Fighter> one(@PathVariable Long id) {
        Fighter fighter = fighterService.getFighter(id);
        return new ResponseEntity<>(fighter, HttpStatus.OK);
    }

    @PostMapping("/api/fighters")
    public ResponseEntity<Fighter> newFighter(@RequestBody Fighter fighter) {
        Fighter savedFighter = fighterService.createFighter(fighter);
        return new ResponseEntity<>(savedFighter, HttpStatus.CREATED);
    }

    @PutMapping("/api/fighters/{id}")
    public ResponseEntity<Fighter> updateFighter(@RequestBody Fighter fighter, @PathVariable Long id) {
        Fighter updatedFighter = fighterService.updateFighter(fighter, id);
        return new ResponseEntity<>(updatedFighter, HttpStatus.OK);
    }

    @DeleteMapping("/api/fighters/{id}")
    public ResponseEntity<?> deleteFighter(@PathVariable Long id) {
        fighterService.deleteFighter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

