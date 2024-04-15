package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.entity.Fight;
import ch.ozan.omar.m295.boxingresfoya.service.FightService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class FightController {
    private final FightService fightService;

    FightController(FightService fightService) {
        this.fightService = fightService;
    }

    @GetMapping("/api/fights")
    public ResponseEntity<List<Fight>> all() {
        List<Fight> fights = fightService.getAllFights();
        return new ResponseEntity<>(fights, HttpStatus.OK);
    }

    @GetMapping("/api/fights/{id}")
    public ResponseEntity<Fight> one(@PathVariable Long id) {
        Fight fight = fightService.getFight(id);
        return new ResponseEntity<>(fight, HttpStatus.OK);
    }

    @PostMapping("/api/fights")
    public ResponseEntity<Fight> newFight(@RequestBody Fight fight) {
        Fight savedFight = fightService.createFight(fight);
        return new ResponseEntity<>(savedFight, HttpStatus.CREATED);
    }

    @PutMapping("/api/fights/{id}")
    public ResponseEntity<Fight> updateFight(@RequestBody Fight fight, @PathVariable Long id) {
        Fight updatedFight = fightService.updateFight(fight, id);
        return new ResponseEntity<>(updatedFight, HttpStatus.OK);
    }

    @DeleteMapping("/api/fights/{id}")
    public ResponseEntity<?> deleteFight(@PathVariable Long id) {
        fightService.deleteFight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

