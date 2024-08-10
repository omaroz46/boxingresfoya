package ch.ozan.omar.m295.boxingresfoya.service;

import ch.ozan.omar.m295.boxingresfoya.entity.Fighter;
import ch.ozan.omar.m295.boxingresfoya.repository.FighterRepository;
import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing Fighter resources.
 */
@Service
public class FighterService {

    private static final Logger logger = LoggerFactory.getLogger(FighterService.class);

    private final FighterRepository fighterRepository;

    /**
     * Constructs a FighterService with the specified FighterRepository.
     *
     * @param fighterRepository the FighterRepository to be used
     */
    public FighterService(FighterRepository fighterRepository) {
        this.fighterRepository = fighterRepository;
    }

    /**
     * Retrieves all fighters.
     *
     * @return a list of all fighters
     */
    @RolesAllowed(Roles.Read)
    public List<Fighter> getAllFighters() {
        logger.info("Fetching all fighters");
        return fighterRepository.findAll();
    }

    /**
     * Retrieves a fighter by its ID.
     *
     * @param id the ID of the fighter
     * @return the fighter with the specified ID
     * @throws EntityNotFoundException if the fighter is not found
     */
    @RolesAllowed(Roles.Read)
    public Fighter getFighter(Long id) {
        logger.info("Fetching fighter with ID: {}", id);
        return fighterRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Fighter with ID: {} not found", id);
                    return new EntityNotFoundException(id, Fighter.class);
                });
    }

    /**
     * Creates a new fighter.
     *
     * @param fighter the fighter to be created
     * @return the created fighter
     */
    @RolesAllowed(Roles.Admin)
    public Fighter createFighter(Fighter fighter) {
        logger.info("Creating fighter: {}", fighter);
        return fighterRepository.save(fighter);
    }

    /**
     * Updates an existing fighter.
     *
     * @param fighter the fighter with updated information
     * @param id the ID of the fighter to be updated
     * @return the updated fighter
     * @throws EntityNotFoundException if the fighter is not found
     */
    @RolesAllowed(Roles.Read)
    public Fighter updateFighter(Fighter fighter, Long id) {
        logger.info("Updating fighter with ID: {} with data: {}", id, fighter);
        return fighterRepository.findById(id)
                .map(existingFighter -> {
                    fighter.setId(id);
                    return fighterRepository.save(fighter);
                })
                .orElseThrow(() -> {
                    logger.error("Fighter with ID: {} not found", id);
                    return new EntityNotFoundException(id, Fighter.class);
                });
    }

    /**
     * Deletes a fighter by its ID.
     *
     * @param id the ID of the fighter to be deleted
     * @throws EntityNotFoundException if the fighter is not found
     */
    @RolesAllowed(Roles.Admin)
    public void deleteFighter(Long id) {
        logger.info("Deleting fighter with ID: {}", id);
        if (!fighterRepository.existsById(id)) {
            logger.error("Fighter with ID: {} not found", id);
            throw new EntityNotFoundException(id, Fighter.class);
        }
        fighterRepository.deleteById(id);
    }
}
