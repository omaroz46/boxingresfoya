package ch.ozan.omar.m295.boxingresfoya.service;
import ch.ozan.omar.m295.boxingresfoya.repository.BoxingClubRepository;
import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import ch.ozan.omar.m295.boxingresfoya.entity.BoxingClub;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxingClubService {

    private final BoxingClubRepository boxingClubRepository;

    public BoxingClubService(BoxingClubRepository boxingClubRepository) {
        this.boxingClubRepository = boxingClubRepository;
    }

    @RolesAllowed(Roles.Read)
    public List<BoxingClub> getAllBoxingClubs() {
        return boxingClubRepository.findAll();
    }

    @RolesAllowed(Roles.Read)
    public BoxingClub getBoxingClub(Long id) {
        return boxingClubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, BoxingClub.class));
    }

    @RolesAllowed(Roles.Admin)
    public BoxingClub createBoxingClub(BoxingClub boxingClub) {
        return boxingClubRepository.save(boxingClub);
    }

    @RolesAllowed(Roles.Read)
    public BoxingClub updateBoxingClub(BoxingClub boxingClub, Long id) {
        return boxingClubRepository.findById(id)
                .map(existingBoxingClub -> {
                    boxingClub.setId(id);
                    return boxingClubRepository.save(boxingClub);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, BoxingClub.class));
    }

    @RolesAllowed(Roles.Admin)
    public void deleteBoxingClub(Long id) {
        if (!boxingClubRepository.existsById(id)) {
            throw new EntityNotFoundException(id, BoxingClub.class);
        }
        boxingClubRepository.deleteById(id);
    }
}
