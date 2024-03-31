package be.kdg.programming5.programming5.controller.web.viewmodel;

import be.kdg.programming5.programming5.model.Course;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * The type Menu item view model.
 */
public class MenuItemViewModel {

    private long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @DecimalMin(value = "0.0", message = "Price must be greater than 0.0")
    private double price;

    @NotNull(message = "Course cannot be null")
    private Course course;

    private boolean vegetarian;

    @Min(value = 0, message = "Spice level must be at least 1")
    private int spiceLvl;

    private List<ChefViewModel> chefs;

    private boolean modificationAllowed;


    /**
     * Instantiates a new Menu item view model.
     */
    public MenuItemViewModel() {
    }

    /**
     * Instantiates a new Menu item view model.
     *
     * @param id                  the id
     * @param name                the name
     * @param price               the price
     * @param course              the course
     * @param vegetarian          the vegetarian
     * @param spiceLvl            the spice lvl
     * @param modificationAllowed the modification allowed
     * @param chefs               the chefs
     */
    public MenuItemViewModel(long id, String name, double price, Course course, boolean vegetarian, int spiceLvl, boolean modificationAllowed, List<ChefViewModel> chefs) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.course = course;
        this.vegetarian = vegetarian;
        this.spiceLvl = spiceLvl;
        this.modificationAllowed = modificationAllowed;
        this.chefs = chefs;
    }

    /**
     * Instantiates a new Menu item view model.
     *
     * @param id                  the id
     * @param name                the name
     * @param price               the price
     * @param course              the course
     * @param vegetarian          the vegetarian
     * @param spiceLvl            the spice lvl
     * @param modificationAllowed the modification allowed
     */
    public MenuItemViewModel(long id, String name, double price, Course course, boolean vegetarian, int spiceLvl, boolean modificationAllowed) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.course = course;
        this.vegetarian = vegetarian;
        this.spiceLvl = spiceLvl;
        this.modificationAllowed = modificationAllowed;
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
