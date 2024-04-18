package ch.ozan.omar.m295.boxingresfoya.storage;

/**
 * Exception thrown when an entity is not found in the storage.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructs a new EntityNotFoundException with the provided ID and entity class.
     * @param id The ID of the entity that was not found.
     * @param c The class of the entity.
     */
    public EntityNotFoundException(Long id, Class c) {
        super("Could not find " + c.getSimpleName() + " " + id);
    }
}