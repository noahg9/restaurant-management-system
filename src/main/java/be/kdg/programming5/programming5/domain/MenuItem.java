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
public class MenuItem extends AbstractEntity<Long> implements Serializable {

    @Column(nullable = false)
    private String name;

    @Column
    private double price;

    @Column
    @Enumerated(EnumType.STRING)
    private Course course;

    @Column
    private boolean vegetarian;

    @Column
    private int spiceLvl;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menuItem")
    private List<MenuItemChef> chefs;

    protected MenuItem() {}

    public MenuItem(String name, double price, Course course, boolean vegetarian, int spiceLvl) {
        setName(name);
        setPrice(price);
        setCourse(course);
        setVegetarian(vegetarian);
        setSpiceLvl(spiceLvl);
    }

    public MenuItem(long id, String name, double price, Course course, boolean vegetarian, int spiceLvl) {
        super(id);
        setName(name);
        setPrice(price);
        setCourse(course);
        setVegetarian(vegetarian);
        setSpiceLvl(spiceLvl);
    }

    public MenuItem(String name, double price, Course course, boolean vegetarian, int spiceLvl, Restaurant restaurant) {
        this(name, price, course, vegetarian, spiceLvl);
        setRestaurant(restaurant);
    }

    public MenuItem(long id, String name, double price, Course course, boolean vegetarian, int spiceLvl, Restaurant restaurant) {
        this(id, name, price, course, vegetarian, spiceLvl);
        setRestaurant(restaurant);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
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

    public boolean isVegetarian() {
        return this.vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
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
    }

    public List<MenuItemChef> getChefs() {
        return this.chefs;
    }

    public void setChefs(List<MenuItemChef> chefs) {
        this.chefs = chefs;
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

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", course=" + course +
                ", vegetarian=" + vegetarian +
                ", spiceLvl=" + spiceLvl +
                ", restaurant=" + restaurant +
                ", chefs=" + chefs +
                '}';
    }
}
