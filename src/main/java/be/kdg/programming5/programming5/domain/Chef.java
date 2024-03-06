package be.kdg.programming5.programming5.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Chef in a restaurant, including personal information and associated menu items.
 * Extends AbstractEntity for common entity properties.
 */
@Entity
@Table(name = "chef")
public class Chef extends AbstractEntity<Long> implements Serializable {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "chef")
    private List<MenuItemChef> menuItems;

    protected Chef() {}

    public Chef(String firstName, String lastName, LocalDate dateOfBirth) {
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
    }

    public Chef(long id, String firstName, String lastName, LocalDate dateOfBirth) {
        super(id);
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
    }

    public Chef(String firstName, String lastName, LocalDate dateOfBirth, Restaurant restaurant) {
        this(firstName, lastName, dateOfBirth);
        setRestaurant(restaurant);
    }

    public Chef(long id, String firstName, String lastName, LocalDate dateOfBirth, Restaurant restaurant) {
        this(id, firstName, lastName, dateOfBirth);
        setRestaurant(restaurant);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.lastName = lastName.trim();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        Objects.requireNonNull(dateOfBirth, "Date of birth cannot be null");
        this.dateOfBirth = dateOfBirth;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        Objects.requireNonNull(restaurant, "Restaurant cannot be null");
        this.restaurant = restaurant;
    }

    public List<MenuItemChef> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemChef> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chef chef = (Chef) o;
        return Objects.equals(id, chef.id) &&
                Objects.equals(firstName, chef.firstName) &&
                Objects.equals(lastName, chef.lastName) &&
                Objects.equals(dateOfBirth, chef.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth);
    }
}
