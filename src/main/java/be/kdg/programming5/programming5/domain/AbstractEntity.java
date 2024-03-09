package be.kdg.programming5.programming5.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

/**
 * The type Abstract entity.
 *
 * @param <T> the type parameter
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
     * Instantiates a new Abstract entity.
     */
    public AbstractEntity() {
    }

    /**
     * Instantiates a new Abstract entity.
     *
     * @param id the id
     */
    public AbstractEntity(T id) {
        this.id = id;
    }

    @Override
    public T getId() {
        return id;
    }

    @Override
    public void setId(T id) {
        this.id = id;
    }
}
