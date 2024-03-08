package be.kdg.programming5.programming5.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Represents a restaurant with details, menu items, and chefs.
 * Extends AbstractEntity for common entity properties.
 */
@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractEntity<Long> implements Serializable {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dateEstablished;

    @Column(nullable = false)
    private int seatingCapacity;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuItem> menuItems;

    @OneToMany(mappedBy = "restaurant")
    private List<Chef> chefs;

    /**
     * Instantiates a new Restaurant.
     */
    protected Restaurant() {}

    /**
     * Instantiates a new Restaurant.
     *
     * @param name            the name
     * @param dateEstablished the date established
     * @param seatingCapacity the seating capacity
     */
    public Restaurant(String name, LocalDate dateEstablished, int seatingCapacity) {
        setName(name);
        setDateEstablished(dateEstablished);
        setSeatingCapacity(seatingCapacity);
    }

    /**
     * Instantiates a new Restaurant.
     *
     * @param id              the id
     * @param name            the name
     * @param dateEstablished the date established
     * @param seatingCapacity the seating capacity
     */
    public Restaurant(long id, String name, LocalDate dateEstablished, int seatingCapacity) {
        super(id);
        setName(name);
        setDateEstablished(dateEstablished);
        setSeatingCapacity(seatingCapacity);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }

    /**
     * Gets date established.
     *
     * @return the date established
     */
    public LocalDate getDateEstablished() {
        return dateEstablished;
    }

    /**
     * Sets date established.
     *
     * @param dateEstablished the date established
     */
    public void setDateEstablished(LocalDate dateEstablished) {
        if (dateEstablished == null) {
            throw new IllegalArgumentException("Date established cannot be null");
        }
        this.dateEstablished = dateEstablished;
    }

    /**
     * Gets seating capacity.
     *
     * @return the seating capacity
     */
    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    /**
     * Sets seating capacity.
     *
     * @param seatingCapacity the seating capacity
     */
    public void setSeatingCapacity(int seatingCapacity) {
        if (seatingCapacity < 0) {
            throw new IllegalArgumentException("Seating capacity cannot be negative");
        }
        this.seatingCapacity = seatingCapacity;
    }

    /**
     * Gets menu items.
     *
     * @return the menu items
     */
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Sets menu items.
     *
     * @param menuItems the menu items
     */
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * Gets chefs.
     *
     * @return the chefs
     */
    public List<Chef> getChefs() {
        return chefs;
    }

    /**
     * Sets chefs.
     *
     * @param chefs the chefs
     */
    public void setChefs(List<Chef> chefs) {
        this.chefs = chefs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateEstablished, seatingCapacity);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", dateEstablished=" + dateEstablished +
                ", seatingCapacity=" + seatingCapacity +
                '}';
    }
}
