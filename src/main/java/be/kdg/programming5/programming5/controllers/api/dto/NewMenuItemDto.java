package be.kdg.programming5.programming5.controllers.api.dto;

import be.kdg.programming5.programming5.domain.Course;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * The type New menu item dto.
 */
public class NewMenuItemDto {
    @NotBlank
    private String name;
    @DecimalMin(value = "0.0")
    private double price;
    private Course course;
    private boolean vegetarian;
    @Min(value = 0)
    private int spiceLvl;

    /**
     * Instantiates a new New menu item dto.
     */
    public NewMenuItemDto() {
    }

    /**
     * Instantiates a new New menu item dto.
     *
     * @param name       the name
     * @param price      the price
     * @param course     the course
     * @param vegetarian the vegetarian
     * @param spiceLvl   the spice lvl
     */
    public NewMenuItemDto(String name, double price, Course course, boolean vegetarian, int spiceLvl) {
        this.name = name;
        this.price = price;
        this.course = course;
        this.vegetarian = vegetarian;
        this.spiceLvl = spiceLvl;
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
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets course.
     *
     * @return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets course.
     *
     * @param course the course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Is vegetarian boolean.
     *
     * @return the boolean
     */
    public boolean isVegetarian() {
        return vegetarian;
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
        return spiceLvl;
    }

    /**
     * Sets spice lvl.
     *
     * @param spiceLvl the spice lvl
     */
    public void setSpiceLvl(int spiceLvl) {
        this.spiceLvl = spiceLvl;
    }
}
