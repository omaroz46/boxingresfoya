package ch.ozan.omar.m295.boxingresfoya.repository;

import ch.ozan.omar.m295.boxingresfoya.entity.Fight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on Fight entities.
 */
@Repository
public interface FightRepository extends JpaRepository<Fight, Long> {
    @Query(name = "Fight.findByLocation")
    List<Fight> findByLocation(String location);
}

