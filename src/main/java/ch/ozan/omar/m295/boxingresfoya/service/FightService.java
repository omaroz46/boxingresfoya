package ch.ozan.omar.m295.boxingresfoya.service;
import ch.ozan.omar.m295.boxingresfoya.entity.Fight;
import ch.ozan.omar.m295.boxingresfoya.repository.FightRepository;
import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for managing Fight entities.
 */
@Service
public class FightService {

    private final FightRepository fightRepository;

    /**
     * Constructs a new FightService with the provided FightRepository.
     * @param fightRepository The repository for Fight entities.
     */
    public FightService(FightRepository fightRepository) {
        this.fightRepository = fightRepository;
    }

    /**
     * Retrieves all Fight entities.
     * @return A list of all Fight entities.
     */
    @RolesAllowed(Roles.Read)
    public List<Fight> getAllFights() {
        return fightRepository.findAll();
    }

    /**
     * Retrieves a single Fight entity by its ID.
     * @param id The ID of the Fight entity to retrieve.
     * @return The requested Fight entity.
     * @throws EntityNotFoundException If the Fight with the given ID is not found.
     */
    @RolesAllowed(Roles.Read)
    public Fight getFight(Long id) {
        return fightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Fight.class));
    }

    /**
     * Creates a new Fight entity.
     * @param fight The Fight entity to create.
     * @return The newly created Fight entity.
     */
    @RolesAllowed(Roles.Admin)
    public Fight createFight(Fight fight) {
        return fightRepository.save(fight);
    }

    /**
     * Updates an existing Fight entity.
     * @param fight The updated Fight entity.
     * @param id The ID of the Fight entity to update.
     * @return The updated Fight entity.
     * @throws EntityNotFoundException If the Fight with the given ID is not found.
     */
    @RolesAllowed(Roles.Read)
    public Fight updateFight(Fight fight, Long id) {
        return fightRepository.findById(id)
                .map(existingFight -> {
                    fight.setId(id);
                    return fightRepository.save(fight);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Fight.class));
    }

    /**
     * Deletes a Fight entity by its ID.
     * @param id The ID of the Fight entity to delete.
     * @throws EntityNotFoundException If the Fight with the given ID is not found.
     */
    @RolesAllowed(Roles.Admin)
    public void deleteFight(Long id) {
        if (!fightRepository.existsById(id)) {
            throw new EntityNotFoundException(id, Fight.class);
        }
        fightRepository.deleteById(id);
    }
}

