package be.kdg.programming5.programming5.controllers.mvc.viewmodel;

import be.kdg.programming5.programming5.domain.Course;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * ViewModel class for adding a menu item, containing necessary information.
 */
public class MenuItemViewModel {

    private int id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @DecimalMin(value = "0.0", message = "Price must be greater than 0.0")
    private double price;

    @NotNull(message = "Course cannot be null")
    private Course course;

    private boolean vegetarian;

    @Min(value = 0, message = "Spice level must be at least 1")
    private int spiceLvl;

    @NotNull(message = "Restaurant ID cannot be null")
    private int restaurantId;

    /**
     * Default constructor for AddMenuItemViewModel.
     */
    public MenuItemViewModel() {
    }

    /**
     * Parameterized constructor for AddMenuItemViewModel.
     *
     * @param name         The name of the menu item.
     * @param price        The price of the menu item.
     * @param course       The course type of the menu item.
     * @param vegetarian   A boolean indicating whether the menu item is vegetarian.
     * @param spiceLvl     The spice level of the menu item.
     * @param restaurantId The ID of the restaurant to which the menu item is associated.
     */
    public MenuItemViewModel(int id, String name, double price, Course course, boolean vegetarian, int spiceLvl, int restaurantId) {
        setId(id);
        setName(name);
        setPrice(price);
        setCourse(course);
        setVegetarian(vegetarian);
        setSpiceLvl(spiceLvl);
        setRestaurantId(restaurantId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the menu item.
     *
     * @return The name of the menu item.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the menu item.
     *
     * @param name The new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the menu item.
     *
     * @return The price of the menu item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the menu item.
     *
     * @param price The new price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the course type of the menu item.
     *
     * @return The course type of the menu item.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets the course type of the menu item.
     *
     * @param course The new course type to set.
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Gets a boolean indicating whether the menu item is vegetarian.
     *
     * @return True if the menu item is vegetarian, false otherwise.
     */
    public boolean isVegetarian() {
        return vegetarian;
    }

    /**
     * Sets whether the menu item is vegetarian.
     *
     * @param vegetarian The new value indicating whether the menu item is vegetarian.
     */
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    /**
     * Gets the spice level of the menu item.
     *
     * @return The spice level of the menu item.
     */
    public int getSpiceLvl() {
        return spiceLvl;
    }

    /**
     * Sets the spice level of the menu item.
     *
     * @param spiceLvl The new spice level to set.
     */
    public void setSpiceLvl(int spiceLvl) {
        this.spiceLvl = spiceLvl;
    }

    /**
     * Gets the ID of the restaurant to which the menu item is associated.
     *
     * @return The restaurant ID.
     */
    public int getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets the restaurant to which the menu item is associated.
     *
     * @param restaurantId The new restaurant ID to set.
     */
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Overrides the toString method to provide a string representation of the AddMenuItemViewModel object.
     *
     * @return A string representation of the AddMenuItemViewModel object.
     */
    @Override
    public String toString() {
        return "AddMenuItemViewModel{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", course=" + course +
                ", vegetarian=" + vegetarian +
                ", spiceLvl=" + spiceLvl +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
