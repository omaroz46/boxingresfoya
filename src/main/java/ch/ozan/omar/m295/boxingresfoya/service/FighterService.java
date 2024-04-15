package ch.ozan.omar.m295.boxingresfoya.service;

import ch.ozan.omar.m295.boxingresfoya.repository.FighterRepository;
import ch.ozan.omar.m295.boxingresfoya.entity.Fighter;
import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FighterService {

    private final FighterRepository fighterRepository;

    public FighterService(FighterRepository fighterRepository) {
        this.fighterRepository = fighterRepository;
    }

    @RolesAllowed(Roles.Read)
    public List<Fighter> getAllFighters() {
        return fighterRepository.findAll();
    }

    @RolesAllowed(Roles.Read)
    public Fighter getFighter(Long id) {
        return fighterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Fighter.class));
    }

    @RolesAllowed(Roles.Admin)
    public Fighter createFighter(Fighter fighter) {
        return fighterRepository.save(fighter);
    }

    @RolesAllowed(Roles.Read)
    public Fighter updateFighter(Fighter fighter, Long id) {
        return fighterRepository.findById(id)
                .map(existingFighter -> {
                    fighter.setId(id);
                    return fighterRepository.save(fighter);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Fighter.class));
    }

    @RolesAllowed(Roles.Admin)
    public void deleteFighter(Long id) {
        if (!fighterRepository.existsById(id)) {
            throw new EntityNotFoundException(id, Fighter.class);
        }
        fighterRepository.deleteById(id);
    }
}
