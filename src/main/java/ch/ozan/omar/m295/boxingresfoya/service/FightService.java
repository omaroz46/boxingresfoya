package ch.ozan.omar.m295.boxingresfoya.service;
import ch.ozan.omar.m295.boxingresfoya.entity.Fight;
import ch.ozan.omar.m295.boxingresfoya.repository.FightRepository;
import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FightService {

    private final FightRepository fightRepository;

    public FightService(FightRepository fightRepository) {
        this.fightRepository = fightRepository;
    }

    @RolesAllowed(Roles.Read)
    public List<Fight> getAllFights() {
        return fightRepository.findAll();
    }

    @RolesAllowed(Roles.Read)
    public Fight getFight(Long id) {
        return fightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Fight.class));
    }

    @RolesAllowed(Roles.Admin)
    public Fight createFight(Fight fight) {
        return fightRepository.save(fight);
    }

    @RolesAllowed(Roles.Read)
    public Fight updateFight(Fight fight, Long id) {
        return fightRepository.findById(id)
                .map(existingFight -> {
                    fight.setId(id);
                    return fightRepository.save(fight);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Fight.class));
    }

    @RolesAllowed(Roles.Admin)
    public void deleteFight(Long id) {
        if (!fightRepository.existsById(id)) {
            throw new EntityNotFoundException(id, Fight.class);
        }
        fightRepository.deleteById(id);
    }
}

