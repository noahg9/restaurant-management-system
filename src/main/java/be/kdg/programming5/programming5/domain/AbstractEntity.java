package be.kdg.programming5.programming5.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

/**
 * An abstract base class for entities in the system.
 *
 * @param <T> The type of identifier for the entity.
 */
@MappedSuperclass
public class AbstractEntity<T extends Serializable> implements Entity<T> {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;

    /**
     * Default constructor for AbstractEntity.
     */
    public AbstractEntity() {
    }

    /**
     * Constructs an AbstractEntity with a specified identifier.
     *
     * @param id The identifier for the entity.
     */
    public AbstractEntity(T id) {
        this.id = id;
    }

    /**
     * Gets the identifier of the entity.
     *
     * @return The identifier of the entity.
     */
    @Override
    public T getId() {
        return id;
    }

    /**
     * Sets the identifier of the entity.
     *
     * @param id The new identifier for the entity.
     */
    @Override
    public void setId(T id) {
        this.id = id;
    }
}
