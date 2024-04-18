package ch.ozan.omar.m295.boxingresfoya.repository;

import ch.ozan.omar.m295.boxingresfoya.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on Event entities.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
