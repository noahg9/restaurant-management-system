package be.kdg.programming5.programming5.model;

import java.io.Serializable;

/**
 * The interface Entity.
 *
 * @param <T> the type parameter
 */
public interface Entity<T extends Serializable> {
    /**
     * Gets id.
     *
     * @return the id
     */
    T getId();

    /**
     * Sets id.
     *
     * @param id the id
     */
    void setId(T id);
}
