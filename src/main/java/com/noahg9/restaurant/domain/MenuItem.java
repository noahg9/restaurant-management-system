package com.noahg9.restaurant.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private int spiceLevel;

    @OneToOne(mappedBy = "menuItem", cascade = CascadeType.ALL)
    private Recipe recipe;

    @OneToMany(mappedBy = "menuItem", fetch = FetchType.EAGER)
    private List<MenuAssignment> chefs;

    /**
     * Instantiates a new Menu item.
     */
    public MenuItem() {
        setName("Unnamed Item " + LocalDate.now());
        setPrice(0);
        setCourse(Course.OTHER);
        setVegetarian(false);
        setSpiceLevel(0);
        setChefs(new ArrayList<>());
    }

    /**
     * Instantiates a new Menu item.
     *
     * @param name       the name
     * @param price      the price
     * @param course     the course
     * @param vegetarian the vegetarian
     * @param spiceLevel the spice level
     */
    public MenuItem(String name, double price, Course course, boolean vegetarian, int spiceLevel) {
        setName(name);
        setPrice(price);
        setCourse(course);
        setVegetarian(vegetarian);
        setSpiceLevel(spiceLevel);
        setChefs(new ArrayList<>());
    }

    /**
     * Instantiates a new Menu item.
     *
     * @param name       the name
     * @param price      the price
     * @param course     the course
     * @param vegetarian the vegetarian
     * @param spiceLevel the spice level
     * @param chefs      the chefs
     */
    public MenuItem(String name, double price, Course course, boolean vegetarian, int spiceLevel, List<MenuAssignment> chefs) {
        setName(name);
        setPrice(price);
        setCourse(course);
        setVegetarian(vegetarian);
        setSpiceLevel(spiceLevel);
        setChefs(chefs);
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
     * Gets spice level.
     *
     * @return the spice level
     */
    public int getSpiceLevel() {
        return this.spiceLevel;
    }

    /**
     * Sets spice level.
     *
     * @param spiceLevel the spice level
     */
    public void setSpiceLevel(int spiceLevel) {
        this.spiceLevel = spiceLevel;
    }

    /**
     * Gets recipe.
     *
     * @return the recipe
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Sets recipe.
     *
     * @param recipe the recipe
     */
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Gets chefs.
     *
     * @return the chefs
     */
    public List<MenuAssignment> getChefs() {
        return this.chefs;
    }

    /**
     * Sets chefs.
     *
     * @param chefs the chefs
     */
    public void setChefs(List<MenuAssignment> chefs) {
        this.chefs = chefs;
    }


    /**
     * Add assigned chef.
     *
     * @param chef the chef
     */
    public void addAssignedChef(Chef chef) {
        if (chef == null) {
            throw new IllegalArgumentException("Chef cannot be null");
        }
        if (chefs == null) {
            chefs = new ArrayList<>();
        } else {
            for (MenuAssignment menuAssignment : chefs) {
                if (menuAssignment.getChef().equals(chef)) {
                    throw new IllegalArgumentException("Chef is already assigned to this menu item");
                }
            }
        }
        MenuAssignment menuAssignment = new MenuAssignment(this, chef);
        chefs.add(menuAssignment);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(id, menuItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", course=" + course +
                ", vegetarian=" + vegetarian +
                ", spiceLevel=" + spiceLevel +
                ", recipe=" + recipe +
                '}';
    }
}
