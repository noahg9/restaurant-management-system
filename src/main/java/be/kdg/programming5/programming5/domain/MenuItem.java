package be.kdg.programming5.programming5.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Represents a menu item in a restaurant, including details, associated restaurant, and chefs.
 * Extends AbstractEntity for common entity properties.
 */
@Entity
@Table(name = "MENU_ITEMS")
public class MenuItem extends AbstractEntity<Integer> implements Serializable {

    private String name;
    private double price;
    @Enumerated(EnumType.STRING)
    private Course course;
    private boolean vegetarian;
    private int spiceLvl;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menuItems")
    private List<MenuItemChef> chefs;

    protected MenuItem() {}

    public MenuItem(String name, double price, Course course, boolean vegetarian, int spiceLvl) {
        this.name = validateString(name, "Name");
        this.price = validateNonNegative(price, "Price");
        this.course = Objects.requireNonNull(course, "Course cannot be null");
        setVegetarian(vegetarian);
        setSpiceLvl(spiceLvl);
    }

    public MenuItem(int id, String name, double price, Course course, boolean vegetarian, int spiceLvl) {
        super(id);
        this.name = validateString(name, "Name");
        this.price = validateNonNegative(price, "Price");
        this.course = Objects.requireNonNull(course, "Course cannot be null");
        setVegetarian(vegetarian);
        setSpiceLvl(spiceLvl);
    }

    public MenuItem(String name, double price, Course course, boolean vegetarian, int spiceLvl, Restaurant restaurant) {
        this(name, price, course, vegetarian, spiceLvl);
        setRestaurant(restaurant);
    }

    public MenuItem(int id, String name, double price, Course course, boolean vegetarian, int spiceLvl, Restaurant restaurant) {
        this(id, name, price, course, vegetarian, spiceLvl);
        setRestaurant(restaurant);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = validateString(name, "Name");
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        this.course = course;
    }

    public boolean getVegetarian() {
        return this.vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        // No validation logic added, can be added if needed
        this.vegetarian = vegetarian;
    }

    public int getSpiceLvl() {
        return this.spiceLvl;
    }

    public void setSpiceLvl(int spiceLvl) {
        this.spiceLvl = spiceLvl;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant cannot be null");
        }
        this.restaurant = restaurant;
        restaurant.addMenuItem(this);
    }

    public List<MenuItemChef> getChefs() {
        return this.chefs;
    }

    public void setChefs(List<MenuItemChef> chefs) {
        this.chefs = chefs;
    }

    /**
     * Generates a random menu item.
     *
     * @return A randomly generated menu item.
     */
    public static MenuItem randomMenuItem() {
        Random random = new Random();
        return new MenuItem(
                "menu item" + random.nextInt(1000),
                random.nextInt(100),
                Course.values()[random.nextInt(Course.values().length)],
                random.nextBoolean(),
                random.nextInt(3)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(id, menuItem.id) &&
                Double.compare(menuItem.price, price) == 0 &&
                vegetarian == menuItem.vegetarian &&
                spiceLvl == menuItem.spiceLvl &&
                Objects.equals(name, menuItem.name) &&
                course == menuItem.course &&
                Objects.equals(restaurant, menuItem.restaurant) &&
                Objects.equals(chefs, menuItem.chefs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, course, vegetarian, spiceLvl, restaurant, chefs);
    }

    private String validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        return value.trim();
    }

    private double validateNonNegative(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return value;
    }
}
