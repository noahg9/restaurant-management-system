package com.noahg9.restaurant.domain;

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
