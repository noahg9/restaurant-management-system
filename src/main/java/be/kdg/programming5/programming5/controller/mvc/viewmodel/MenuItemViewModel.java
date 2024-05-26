package be.kdg.programming5.programming5.controller.mvc.viewmodel;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * The type Menu item view model.
 */
public class MenuItemViewModel {
    private long id;
    private String name;
    private double price;
    private String courseName;
    private boolean vegetarian;
    private int spiceLevel;

    private boolean modificationAllowed;

    private List<ChefViewModel> chefs;

    /**
     * Instantiates a new Menu item view model.
     *
     * @param id                  the id
     * @param name                the name
     * @param price               the price
     * @param courseName          the course
     * @param vegetarian          the vegetarian
     * @param spiceLevel          the spice level
     * @param modificationAllowed the modification allowed
     */
    public MenuItemViewModel(long id, String name, double price, String courseName, boolean vegetarian, int spiceLevel, boolean modificationAllowed) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.courseName = courseName;
        this.vegetarian = vegetarian;
        this.spiceLevel = spiceLevel;
        this.modificationAllowed = modificationAllowed;
    }

    /**
     * Instantiates a new Menu item view model.
     *
     * @param id                  the id
     * @param name                the name
     * @param price               the price
     * @param courseName          the course
     * @param vegetarian          the vegetarian
     * @param spiceLevel          the spice level
     * @param modificationAllowed the modification allowed
     * @param chefs               the chefs
     */
    public MenuItemViewModel(long id, String name, double price, String courseName, boolean vegetarian, int spiceLevel, boolean modificationAllowed, List<ChefViewModel> chefs) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.courseName = courseName;
        this.vegetarian = vegetarian;
        this.spiceLevel = spiceLevel;
        this.modificationAllowed = modificationAllowed;
        this.chefs = chefs;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
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
     * Gets chefs.
     *
     * @return the chefs
     */
    public List<ChefViewModel> getChefs() {
        return chefs;
    }

    /**
     * Sets chefs.
     *
     * @param chefs the chefs
     */
    public void setChefs(List<ChefViewModel> chefs) {
        this.chefs = chefs;
    }

    /**
     * Is modification allowed boolean.
     *
     * @return the boolean
     */
    public boolean isModificationAllowed() {
        return modificationAllowed;
    }

    /**
     * Sets modification allowed.
     *
     * @param modificationAllowed the modification allowed
     */
    public void setModificationAllowed(boolean modificationAllowed) {
        this.modificationAllowed = modificationAllowed;
    }
}
