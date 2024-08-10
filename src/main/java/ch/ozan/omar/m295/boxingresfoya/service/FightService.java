package ch.ozan.omar.m295.boxingresfoya.service;

import ch.ozan.omar.m295.boxingresfoya.entity.Fight;
import ch.ozan.omar.m295.boxingresfoya.repository.FightRepository;
import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing Fight resources.
 */
@Service
public class FightService {

    private static final Logger logger = LoggerFactory.getLogger(FightService.class);

    private final FightRepository fightRepository;

    /**
     * Constructs a FightService with the specified FightRepository.
     *
     * @param fightRepository the FightRepository to be used
     */
    public FightService(FightRepository fightRepository) {
        this.fightRepository = fightRepository;
    }

    /**
     * Retrieves all fights.
     *
     * @return a list of all fights
     */
    @RolesAllowed(Roles.Read)
    public List<Fight> getAllFights() {
        logger.info("Fetching all fights");
        return fightRepository.findAll();
    }

    /**
     * Retrieves a fight by its ID.
     *
     * @param id the ID of the fight
     * @return the fight with the specified ID
     * @throws EntityNotFoundException if the fight is not found
     */
    @RolesAllowed(Roles.Read)
    public Fight getFight(Long id) {
        logger.info("Fetching fight with ID: {}", id);
        return fightRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Fight with ID: {} not found", id);
                    return new EntityNotFoundException(id, Fight.class);
                });
    }

    /**
     * Creates a new fight.
     *
     * @param fight the fight to be created
     * @return the created fight
     */
    @RolesAllowed(Roles.Admin)
    public Fight createFight(Fight fight) {
        logger.info("Creating fight: {}", fight);
        return fightRepository.save(fight);
    }

    /**
     * Updates an existing fight.
     *
     * @param fight the fight with updated information
     * @param id the ID of the fight to be updated
     * @return the updated fight
     * @throws EntityNotFoundException if the fight is not found
     */
    @RolesAllowed(Roles.Read)
    public Fight updateFight(Fight fight, Long id) {
        logger.info("Updating fight with ID: {} with data: {}", id, fight);
        return fightRepository.findById(id)
                .map(existingFight -> {
                    fight.setId(id);
                    return fightRepository.save(fight);
                })
                .orElseThrow(() -> {
                    logger.error("Fight with ID: {} not found", id);
                    return new EntityNotFoundException(id, Fight.class);
                });
    }

    /**
     * Deletes a fight by its ID.
     *
     * @param id the ID of the fight to be deleted
     * @throws EntityNotFoundException if the fight is not found
     */
    @RolesAllowed(Roles.Admin)
    public void deleteFight(Long id) {
        logger.info("Deleting fight with ID: {}", id);
        if (!fightRepository.existsById(id)) {
            logger.error("Fight with ID: {} not found", id);
            throw new EntityNotFoundException(id, Fight.class);
        }
        fightRepository.deleteById(id);
    }
}
