package ch.ozan.omar.m295.boxingresfoya.repository;

import ch.ozan.omar.m295.boxingresfoya.entity.BoxingClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on BoxingClub entities.
 */
@Repository
public interface BoxingClubRepository extends JpaRepository<BoxingClub, Long> {
}
