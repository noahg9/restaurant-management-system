package be.kdg.programming5.programming5.dto;

import be.kdg.programming5.programming5.model.Course;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * The type New menu item dto.
 */
public class NewMenuItemDto {
    @NotBlank
    private String name;
    @DecimalMin(value = "0.0")
    private double price;
    @NotBlank
    private String courseName;
    @NotNull
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
     * @param courseName     the course
     * @param vegetarian the vegetarian
     * @param spiceLvl   the spice lvl
     */
    public NewMenuItemDto(String name, double price, String courseName, boolean vegetarian, int spiceLvl) {
        this.name = name;
        this.price = price;
        this.courseName = courseName;
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
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets course.
     *
     * @param courseName the course
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
