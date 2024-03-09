package be.kdg.programming5.programming5.controllers.mvc.viewmodel;

import be.kdg.programming5.programming5.domain.Course;
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

    private long restaurantId;

    private String restaurantName;

    private List<ChefViewModel> chefs;


    /**
     * Instantiates a new Menu item view model.
     */
    public MenuItemViewModel() {
    }

    /**
     * Instantiates a new Menu item view model.
     *
     * @param id             the id
     * @param name           the name
     * @param price          the price
     * @param course         the course
     * @param vegetarian     the vegetarian
     * @param spiceLvl       the spice lvl
     * @param restaurantId   the restaurant id
     * @param restaurantName the restaurant name
     */
    public MenuItemViewModel(long id, String name, double price, Course course, boolean vegetarian, int spiceLvl, long restaurantId, String restaurantName) {
        setId(id);
        setName(name);
        setPrice(price);
        setCourse(course);
        setVegetarian(vegetarian);
        setSpiceLvl(spiceLvl);
        setRestaurantId(restaurantId);
        setRestaurantName(restaurantName);
    }

    /**
     * Instantiates a new Menu item view model.
     *
     * @param id             the id
     * @param name           the name
     * @param price          the price
     * @param course         the course
     * @param vegetarian     the vegetarian
     * @param spiceLvl       the spice lvl
     * @param restaurantId   the restaurant id
     * @param restaurantName the restaurant name
     * @param chefs          the chefs
     */
    public MenuItemViewModel(long id, String name, double price, Course course, boolean vegetarian, int spiceLvl, long restaurantId, String restaurantName, List<ChefViewModel> chefs) {
        setId(id);
        setName(name);
        setPrice(price);
        setCourse(course);
        setVegetarian(vegetarian);
        setSpiceLvl(spiceLvl);
        setRestaurantId(restaurantId);
        setRestaurantName(restaurantName);
        setChefs(chefs);
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
     * Gets restaurant id.
     *
     * @return the restaurant id
     */
    public long getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets restaurant id.
     *
     * @param restaurantId the restaurant id
     */
    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Gets restaurant name.
     *
     * @return the restaurant name
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Sets restaurant name.
     *
     * @param restaurantName the restaurant name
     */
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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
}
