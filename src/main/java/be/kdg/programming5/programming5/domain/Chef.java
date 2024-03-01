package be.kdg.programming5.programming5.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Represents a Chef in a restaurant, including personal information and associated menu items.
 * Extends AbstractEntity for common entity properties.
 */
@Entity
@Table(name = "CHEFS")
public class Chef extends AbstractEntity<Integer> implements Serializable {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "chef")
    private List<MenuItemChef> menuItems;

    protected Chef() {}

    public Chef(String firstName, String lastName, LocalDate dateOfBirth) {
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
    }

    public Chef(int id, String firstName, String lastName, LocalDate dateOfBirth) {
        super(id);
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
    }

    public Chef(String firstName, String lastName, LocalDate dateOfBirth, Restaurant restaurant) {
        this(firstName, lastName, dateOfBirth);
        setRestaurant(restaurant);
    }

    public Chef(int id, String firstName, String lastName, LocalDate dateOfBirth, Restaurant restaurant) {
        this(id, firstName, lastName, dateOfBirth);
        setRestaurant(restaurant);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        validateName(firstName, "First name");
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        validateName(lastName, "Last name");
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
        restaurant.addChef(this);
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
        return id == chef.id &&
                Objects.equals(firstName, chef.firstName) &&
                Objects.equals(lastName, chef.lastName) &&
                Objects.equals(dateOfBirth, chef.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth);
    }

    public static Chef randomChef() {
        Random random = new Random();
        return new Chef(
                "chef",
                "#" + random.nextInt(1000),
                LocalDate.of(1940 + random.nextInt(60), random.nextInt(12) + 1, random.nextInt(27) + 1)
        );
    }

    private void validateName(String name, String fieldName) {
        Objects.requireNonNull(name, fieldName + " cannot be null");
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
    }
}
