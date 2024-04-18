package ch.ozan.omar.m295.boxingresfoya.service;
import ch.ozan.omar.m295.boxingresfoya.repository.BoxingClubRepository;
import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import ch.ozan.omar.m295.boxingresfoya.entity.BoxingClub;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing BoxingClub entities.
 */
@Service
public class BoxingClubService {

    private final BoxingClubRepository boxingClubRepository;

    /**
     * Constructs a new BoxingClubService with the provided BoxingClubRepository.
     * @param boxingClubRepository The repository for BoxingClub entities.
     */
    public BoxingClubService(BoxingClubRepository boxingClubRepository) {
        this.boxingClubRepository = boxingClubRepository;
    }

    /**
     * Retrieves all BoxingClub entities.
     * @return A list of all BoxingClub entities.
     */
    @RolesAllowed(Roles.Read)
    public List<BoxingClub> getAllBoxingClubs() {
        return boxingClubRepository.findAll();
    }

    /**
     * Retrieves a single BoxingClub entity by its ID.
     * @param id The ID of the BoxingClub entity to retrieve.
     * @return The requested BoxingClub entity.
     * @throws EntityNotFoundException If the BoxingClub with the given ID is not found.
     */
    @RolesAllowed(Roles.Read)
    public BoxingClub getBoxingClub(Long id) {
        return boxingClubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, BoxingClub.class));
    }

    /**
     * Creates a new BoxingClub entity.
     * @param boxingClub The BoxingClub entity to create.
     * @return The newly created BoxingClub entity.
     */
    @RolesAllowed(Roles.Admin)
    public BoxingClub createBoxingClub(BoxingClub boxingClub) {
        return boxingClubRepository.save(boxingClub);
    }

    /**
     * Updates an existing BoxingClub entity.
     * @param boxingClub The updated BoxingClub entity.
     * @param id The ID of the BoxingClub entity to update.
     * @return The updated BoxingClub entity.
     * @throws EntityNotFoundException If the BoxingClub with the given ID is not found.
     */
    @RolesAllowed(Roles.Read)
    public BoxingClub updateBoxingClub(BoxingClub boxingClub, Long id) {
        return boxingClubRepository.findById(id)
                .map(existingBoxingClub -> {
                    boxingClub.setId(id);
                    return boxingClubRepository.save(boxingClub);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, BoxingClub.class));
    }

    /**
     * Deletes a BoxingClub entity by its ID.
     * @param id The ID of the BoxingClub entity to delete.
     * @throws EntityNotFoundException If the BoxingClub with the given ID is not found.
     */
    @RolesAllowed(Roles.Admin)
    public void deleteBoxingClub(Long id) {
        if (!boxingClubRepository.existsById(id)) {
            throw new EntityNotFoundException(id, BoxingClub.class);
        }
        boxingClubRepository.deleteById(id);
    }
}
