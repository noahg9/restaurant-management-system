package be.kdg.programming5.programming5.controllers.mvc.viewmodel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * The type Chef view model.
 */
public class ChefViewModel {

    private long id;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @NotNull(message = "Date of birth cannot be null")
    private LocalDate dateOfBirth;

    private long restaurantId;

    private String restaurantName;

    private List<MenuItemViewModel> menuItems;

    /**
     * Instantiates a new Chef view model.
     */
    public ChefViewModel() {}

    /**
     * Instantiates a new Chef view model.
     *
     * @param id             the id
     * @param firstName      the first name
     * @param lastName       the last name
     * @param dateOfBirth    the date of birth
     * @param restaurantId   the restaurant id
     * @param restaurantName the restaurant name
     */
    public ChefViewModel(long id, String firstName, String lastName, LocalDate dateOfBirth, long restaurantId, String restaurantName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
    }

    /**
     * Instantiates a new Chef view model.
     *
     * @param id             the id
     * @param firstName      the first name
     * @param lastName       the last name
     * @param dateOfBirth    the date of birth
     * @param restaurantId   the restaurant id
     * @param restaurantName the restaurant name
     * @param menuItems      the menu items
     */
    public ChefViewModel(long id, String firstName, String lastName, LocalDate dateOfBirth, long restaurantId, String restaurantName, List<MenuItemViewModel> menuItems) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.menuItems = menuItems;
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
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
     * Calculate age int.
     *
     * @return the int
     */
    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }

    /**
     * Gets menu items.
     *
     * @return the menu items
     */
    public List<MenuItemViewModel> getMenuItems() {
        return menuItems;
    }

    /**
     * Sets menu items.
     *
     * @param menuItems the menu items
     */
    public void setMenuItems(List<MenuItemViewModel> menuItems) {
        this.menuItems = menuItems;
    }
}
