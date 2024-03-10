package be.kdg.programming5.programming5.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

/**
 * The type Chef.
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

    /**
     * Instantiates a new Chef.
     */
    protected Chef() {
    }

    /**
     * Instantiates a new Chef.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     */
    public Chef(String firstName, String lastName, LocalDate dateOfBirth) {
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
    }

    /**
     * Instantiates a new Chef.
     *
     * @param id          the id
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     */
    public Chef(long id, String firstName, String lastName, LocalDate dateOfBirth) {
        super(id);
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
    }

    /**
     * Instantiates a new Chef.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     * @param restaurant  the restaurant
     */
    public Chef(String firstName, String lastName, LocalDate dateOfBirth, Restaurant restaurant) {
        this(firstName, lastName, dateOfBirth);
        setRestaurant(restaurant);
    }

    /**
     * Instantiates a new Chef.
     *
     * @param id          the id
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     * @param restaurant  the restaurant
     */
    public Chef(long id, String firstName, String lastName, LocalDate dateOfBirth, Restaurant restaurant) {
        this(id, firstName, lastName, dateOfBirth);
        setRestaurant(restaurant);
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName.trim();
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.lastName = lastName.trim();
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        Objects.requireNonNull(dateOfBirth, "Date of birth cannot be null");
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets restaurant.
     *
     * @return the restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Sets restaurant.
     *
     * @param restaurant the restaurant
     */
    public void setRestaurant(Restaurant restaurant) {
        Objects.requireNonNull(restaurant, "Restaurant cannot be null");
        this.restaurant = restaurant;
    }

    /**
     * Gets menu items.
     *
     * @return the menu items
     */
    public List<MenuItemChef> getMenuItems() {
        return menuItems;
    }

    /**
     * Sets menu items.
     *
     * @param menuItems the menu items
     */
    public void setMenuItems(List<MenuItemChef> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chef chef = (Chef) o;
        return Objects.equals(id, chef.id) && Objects.equals(firstName, chef.firstName) && Objects.equals(lastName, chef.lastName) && Objects.equals(dateOfBirth, chef.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth);
    }
}
