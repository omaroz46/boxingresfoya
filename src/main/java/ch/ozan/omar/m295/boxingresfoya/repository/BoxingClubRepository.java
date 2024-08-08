package ch.ozan.omar.m295.boxingresfoya.repository;

import ch.ozan.omar.m295.boxingresfoya.entity.BoxingClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxingClubRepository extends JpaRepository<BoxingClub, Long> {

    @Query(name = "BoxingClub.findByName")
    List<BoxingClub> findByName(String name);
}
