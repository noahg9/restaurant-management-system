package be.kdg.programming5.programming5.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

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
    @Column
    private String name;

    @Column
    private LocalDate dateEstablished;

    @Column
    private int seatingCapacity;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuItem> menuItems;

    @OneToMany(mappedBy = "restaurant")
    private List<Chef> chefs;

    protected Restaurant() {}

    public Restaurant(String name, LocalDate dateEstablished, int seatingCapacity) {
        setName(name);
        setDateEstablished(dateEstablished);
        setSeatingCapacity(seatingCapacity);
    }

    public Restaurant(long id, String name, LocalDate dateEstablished, int seatingCapacity) {
        super(id);
        setName(name);
        setDateEstablished(dateEstablished);
        setSeatingCapacity(seatingCapacity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }

    public LocalDate getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(LocalDate dateEstablished) {
        if (dateEstablished == null) {
            throw new IllegalArgumentException("Date established cannot be null");
        }
        this.dateEstablished = dateEstablished;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        if (seatingCapacity < 0) {
            throw new IllegalArgumentException("Seating capacity cannot be negative");
        }
        this.seatingCapacity = seatingCapacity;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<Chef> getChefs() {
        return chefs;
    }

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
