package ch.ozan.omar.m295.boxingresfoya.service;

import ch.ozan.omar.m295.boxingresfoya.repository.FighterRepository;
import ch.ozan.omar.m295.boxingresfoya.entity.Fighter;
import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for managing Fighter entities.
 */
@Service
public class FighterService {

    private final FighterRepository fighterRepository;

    /**
     * Constructs a new FighterService with the provided FighterRepository.
     * @param fighterRepository The repository for Fighter entities.
     */
    public FighterService(FighterRepository fighterRepository) {
        this.fighterRepository = fighterRepository;
    }

    /**
     * Retrieves all Fighter entities.
     * @return A list of all Fighter entities.
     */
    @RolesAllowed(Roles.Read)
    public List<Fighter> getAllFighters() {
        return fighterRepository.findAll();
    }

    /**
     * Retrieves a single Fighter entity by its ID.
     * @param id The ID of the Fighter entity to retrieve.
     * @return The requested Fighter entity.
     * @throws EntityNotFoundException If the Fighter with the given ID is not found.
     */
    @RolesAllowed(Roles.Read)
    public Fighter getFighter(Long id) {
        return fighterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Fighter.class));
    }

    /**
     * Creates a new Fighter entity.
     * @param fighter The Fighter entity to create.
     * @return The newly created Fighter entity.
     */
    @RolesAllowed(Roles.Admin)
    public Fighter createFighter(Fighter fighter) {
        return fighterRepository.save(fighter);
    }

    /**
     * Updates an existing Fighter entity.
     * @param fighter The updated Fighter entity.
     * @param id The ID of the Fighter entity to update.
     * @return The updated Fighter entity.
     * @throws EntityNotFoundException If the Fighter with the given ID is not found.
     */
    @RolesAllowed(Roles.Read)
    public Fighter updateFighter(Fighter fighter, Long id) {
        return fighterRepository.findById(id)
                .map(existingFighter -> {
                    fighter.setId(id);
                    return fighterRepository.save(fighter);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Fighter.class));
    }

    /**
     * Deletes a Fighter entity by its ID.
     * @param id The ID of the Fighter entity to delete.
     * @throws EntityNotFoundException If the Fighter with the given ID is not found.
     */
    @RolesAllowed(Roles.Admin)
    public void deleteFighter(Long id) {
        if (!fighterRepository.existsById(id)) {
            throw new EntityNotFoundException(id, Fighter.class);
        }
        fighterRepository.deleteById(id);
    }
}
