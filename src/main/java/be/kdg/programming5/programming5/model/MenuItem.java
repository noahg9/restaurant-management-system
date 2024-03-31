package be.kdg.programming5.programming5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

/**
 * The type Menu item.
 */
@Entity
@Table(name = "menu_item")
public class MenuItem extends AbstractEntity<Long> implements Serializable {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Course course;

    @Column(nullable = false)
    private boolean vegetarian;

    @Column(nullable = false)
    private int spiceLvl;

    @OneToMany(mappedBy = "menuItem")
    private List<AssignedChef> chefs;

    /**
     * Instantiates a new Menu item.
     */
    protected MenuItem() {}

    /**
     * Instantiates a new Menu item.
     *
     * @param name       the name
     * @param price      the price
     * @param course     the course
     * @param vegetarian the vegetarian
     * @param spiceLvl   the spice lvl
     */
    public MenuItem(String name, double price, Course course, boolean vegetarian, int spiceLvl) {
        setName(name);
        setPrice(price);
        setCourse(course);
        setVegetarian(vegetarian);
        setSpiceLvl(spiceLvl);
    }

    /**
     * Instantiates a new Menu item.
     *
     * @param id         the id
     * @param name       the name
     * @param price      the price
     * @param course     the course
     * @param vegetarian the vegetarian
     * @param spiceLvl   the spice lvl
     */
    public MenuItem(long id, String name, double price, Course course, boolean vegetarian, int spiceLvl) {
        super(id);
        setName(name);
        setPrice(price);
        setCourse(course);
        setVegetarian(vegetarian);
        setSpiceLvl(spiceLvl);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
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
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    /**
     * Gets course.
     *
     * @return the course
     */
    public Course getCourse() {
        return this.course;
    }

    /**
     * Sets course.
     *
     * @param course the course
     */
    public void setCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        this.course = course;
    }

    /**
     * Is vegetarian boolean.
     *
     * @return the boolean
     */
    public boolean isVegetarian() {
        return this.vegetarian;
    }

    /**
     * Sets vegetarian.
     *
     * @param vegetarian the vegetarian
     */
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    /**
     * Gets spice lvl.
     *
     * @return the spice lvl
     */
    public int getSpiceLvl() {
        return this.spiceLvl;
    }

    /**
     * Sets spice lvl.
     *
     * @param spiceLvl the spice lvl
     */
    public void setSpiceLvl(int spiceLvl) {
        this.spiceLvl = spiceLvl;
    }

    /**
     * Gets chefs.
     *
     * @return the chefs
     */
    public List<AssignedChef> getChefs() {
        return this.chefs;
    }

    /**
     * Sets chefs.
     *
     * @param chefs the chefs
     */
    public void setChefs(List<AssignedChef> chefs) {
        this.chefs = chefs;
    }
}
