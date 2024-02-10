package be.kdg.programming5.programming5.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Represents a restaurant with details, menu items, and chefs.
 * Extends AbstractEntity for common entity properties.
 */
@Entity
@Table(name = "RESTAURANTS")
public class Restaurant extends AbstractEntity<Integer> implements Serializable {

    private String name;
    private LocalDate dateEstablished;
    private int seatingCapacity;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuItem> menuItems = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Chef> chefs = new ArrayList<>();

    protected Restaurant() {}

    public Restaurant(String name, LocalDate dateEstablished, int seatingCapacity) {
        this.name = validateString(name, "Name");
        this.dateEstablished = dateEstablished;
        this.seatingCapacity = seatingCapacity;
    }

    public Restaurant(int id, String name, LocalDate dateEstablished, int seatingCapacity) {
        super(id);
        this.name = validateString(name, "Name");
        this.dateEstablished = dateEstablished;
        this.seatingCapacity = seatingCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = validateString(name, "Name");
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

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(Objects.requireNonNull(menuItem, "MenuItem cannot be null"));
    }

    public List<Chef> getChefs() {
        return chefs;
    }

    public void setChefs(List<Chef> chefs) {
        this.chefs = chefs;
    }

    public void addChef(Chef chef) {
        chefs.add(Objects.requireNonNull(chef, "Chef cannot be null"));
    }

    /**
     * Generates a random restaurant.
     *
     * @return A randomly generated restaurant.
     */
    public static Restaurant randomRestaurant() {
        Random random = new Random();
        return new Restaurant(
                "restaurant" + random.nextInt(1000),
                LocalDate.of(1920 + random.nextInt(100), random.nextInt(12) + 1, random.nextInt(27) + 1),
                random.nextInt(200)
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Restaurant {")
                .append("id=").append(id)
                .append(", name='").append(name).append('\'')
                .append(", dateEstablished=").append(dateEstablished)
                .append(", seatingCapacity=").append(seatingCapacity)
                .append(", menuItems=").append(getItemsString(menuItems))
                .append(", chefs=").append(getItemsString(chefs))
                .append("}");
        return sb.toString();
    }

    private String getItemsString(List<?> items) {
        StringBuilder sb = new StringBuilder("[");
        if (items != null && !items.isEmpty()) {
            for (Object item : items) {
                sb.append(item.toString()).append(", ");
            }
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return id == that.id &&
                seatingCapacity == that.seatingCapacity &&
                Objects.equals(name, that.name) &&
                Objects.equals(dateEstablished, that.dateEstablished) &&
                Objects.equals(menuItems, that.menuItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateEstablished, seatingCapacity, menuItems);
    }

    @NotNull
    static String getString(StringBuilder sb, List<?> items) {
        if (items != null && !items.isEmpty()) {
            for (Object item : items) {
                sb.append(item.toString()).append(", ");
            }
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }
        return sb.toString();
    }

    private String validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        return value.trim();
    }

    private int validateNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return value;
    }
}
