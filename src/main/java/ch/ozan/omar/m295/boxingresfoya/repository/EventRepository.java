package ch.ozan.omar.m295.boxingresfoya.repository;

import ch.ozan.omar.m295.boxingresfoya.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on Event entities.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(name = "Event.findByTitle")
    List<Event> findByTitle(String title);
}
