package ch.ozan.omar.m295.boxingresfoya.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseRepository<T, ID> implements JpaRepository<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
