package be.kdg.programming5.programming5.dto;

import jakarta.validation.constraints.*;

import java.util.List;

/**
 * The type New menu item dto.
 */
public class NewMenuItemDto {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    @DecimalMin(value = "0.0")
    private double price;
    @NotNull
    @NotBlank
    private String courseName;
    @NotNull
    private boolean vegetarian;
    @NotNull
    @Min(value = 0)
    private int spiceLevel;

    /**
     * The Chef ids.
     */
    List<Long> chefIds;

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
     * @param courseName the course name
     * @param vegetarian the vegetarian
     * @param spiceLevel the spice level
     */
    public NewMenuItemDto(String name, double price, String courseName, boolean vegetarian, int spiceLevel) {
        this.name = name;
        this.price = price;
        this.courseName = courseName;
        this.vegetarian = vegetarian;
        this.spiceLevel = spiceLevel;
    }

    /**
     * Instantiates a new New menu item dto.
     *
     * @param name       the name
     * @param price      the price
     * @param courseName the course name
     * @param vegetarian the vegetarian
     * @param spiceLevel the spice level
     * @param chefIds    the chef ids
     */
    public NewMenuItemDto(String name, double price, String courseName, boolean vegetarian, int spiceLevel, List<Long> chefIds) {
        this.name = name;
        this.price = price;
        this.courseName = courseName;
        this.vegetarian = vegetarian;
        this.spiceLevel = spiceLevel;
        this.chefIds = chefIds;
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
     * Gets course name.
     *
     * @return the course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets course name.
     *
     * @param courseName the course name
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
     * Gets spice level.
     *
     * @return the spice level
     */
    public int getSpiceLevel() {
        return spiceLevel;
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
     * Gets chef ids.
     *
     * @return the chef ids
     */
    public List<Long> getChefIds() {
        return chefIds;
    }

    /**
     * Sets chef ids.
     *
     * @param chefIds the chef ids
     */
    public void setChefIds(List<Long> chefIds) {
        this.chefIds = chefIds;
    }
}
