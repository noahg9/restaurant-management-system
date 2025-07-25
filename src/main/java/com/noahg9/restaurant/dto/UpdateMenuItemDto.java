package com.noahg9.restaurant.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * The type Update menu item dto.
 */
public class UpdateMenuItemDto {
    @NotBlank
    private String name;
    @DecimalMin(value = "0.0")
    private double price;
    private boolean vegetarian;
    @Min(value = 0)
    private int spiceLevel;

    /**
     * Instantiates a new Update menu item dto.
     */
    public UpdateMenuItemDto() {
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
}
