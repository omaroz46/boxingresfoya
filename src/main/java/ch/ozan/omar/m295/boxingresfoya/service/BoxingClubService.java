package ch.ozan.omar.m295.boxingresfoya.service;

import ch.ozan.omar.m295.boxingresfoya.entity.BoxingClub;
import ch.ozan.omar.m295.boxingresfoya.repository.BoxingClubRepository;
import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing Boxing Club resources.
 */
@Service
public class BoxingClubService {

    private static final Logger logger = LoggerFactory.getLogger(BoxingClubService.class);

    private final BoxingClubRepository boxingClubRepository;

    /**
     * Constructs a BoxingClubService with the specified BoxingClubRepository.
     *
     * @param boxingClubRepository the BoxingClubRepository to be used
     */
    public BoxingClubService(BoxingClubRepository boxingClubRepository) {
        this.boxingClubRepository = boxingClubRepository;
    }

    /**
     * Retrieves all boxing clubs.
     *
     * @return a list of all boxing clubs
     */
    @RolesAllowed(Roles.Read)
    public List<BoxingClub> getAllBoxingClubs() {
        logger.info("Fetching all boxing clubs");
        return boxingClubRepository.findAll();
    }

    /**
     * Retrieves a boxing club by its ID.
     *
     * @param id the ID of the boxing club
     * @return the boxing club with the specified ID
     * @throws EntityNotFoundException if the boxing club is not found
     */
    @RolesAllowed(Roles.Read)
    public BoxingClub getBoxingClub(Long id) {
        logger.info("Fetching boxing club with ID: {}", id);
        return boxingClubRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Boxing club with ID: {} not found", id);
                    return new EntityNotFoundException(id, BoxingClub.class);
                });
    }

    /**
     * Creates a new boxing club.
     *
     * @param boxingClub the boxing club to be created
     * @return the created boxing club
     */
    @RolesAllowed(Roles.Admin)
    public BoxingClub createBoxingClub(BoxingClub boxingClub) {
        logger.info("Creating boxing club: {}", boxingClub);
        return boxingClubRepository.save(boxingClub);
    }

    /**
     * Updates an existing boxing club.
     *
     * @param boxingClub the boxing club with updated information
     * @param id the ID of the boxing club to be updated
     * @return the updated boxing club
     * @throws EntityNotFoundException if the boxing club is not found
     */
    @RolesAllowed(Roles.Read)
    public BoxingClub updateBoxingClub(BoxingClub boxingClub, Long id) {
        logger.info("Updating boxing club with ID: {} with data: {}", id, boxingClub);
        return boxingClubRepository.findById(id)
                .map(existingBoxingClub -> {
                    boxingClub.setId(id);
                    return boxingClubRepository.save(boxingClub);
                })
                .orElseThrow(() -> {
                    logger.error("Boxing club with ID: {} not found", id);
                    return new EntityNotFoundException(id, BoxingClub.class);
                });
    }

    /**
     * Deletes a boxing club by its ID.
     *
     * @param id the ID of the boxing club to be deleted
     * @throws EntityNotFoundException if the boxing club is not found
     */
    @RolesAllowed(Roles.Admin)
    public void deleteBoxingClub(Long id) {
        logger.info("Deleting boxing club with ID: {}", id);
        if (!boxingClubRepository.existsById(id)) {
            logger.error("Boxing club with ID: {} not found", id);
            throw new EntityNotFoundException(id, BoxingClub.class);
        }
        boxingClubRepository.deleteById(id);
    }
}
