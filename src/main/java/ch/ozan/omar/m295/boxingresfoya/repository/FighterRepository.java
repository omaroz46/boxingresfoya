package ch.ozan.omar.m295.boxingresfoya.repository;

import ch.ozan.omar.m295.boxingresfoya.entity.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on Fighter entities.
 */
@Repository
public interface FighterRepository extends JpaRepository<Fighter, Long> {
}

