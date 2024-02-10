package be.kdg.programming5.programming5.domain;

import java.io.Serializable;

/**
 * An interface representing an entity in the system with a generic identifier.
 *
 * @param <T> The type of identifier for the entity.
 */
public interface Entity<T extends Serializable> {
    /**
     * Gets the identifier of the entity.
     *
     * @return The identifier of the entity.
     */
    T getId();

    /**
     * Sets the identifier of the entity.
     *
     * @param id The new identifier for the entity.
     */
    void setId(T id);
}
