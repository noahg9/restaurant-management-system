package be.kdg.programming5.programming5.controllers.mvc.viewmodel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * ViewModel class for adding a Chef, containing necessary information.
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
     * Default constructor for AddChefViewModel.
     */
    public ChefViewModel() {}

    /**
     * Parameterized constructor for AddChefViewModel.
     *
     * @param firstName    The first name of the chef.
     * @param lastName     The last name of the chef.
     * @param dateOfBirth  The date of birth of the chef.
     * @param restaurantId   The restaurant to which the chef is associated.
     */
    public ChefViewModel(long id, String firstName, String lastName, LocalDate dateOfBirth, long restaurantId, String restaurantName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
    }

    public ChefViewModel(long id, String firstName, String lastName, LocalDate dateOfBirth, long restaurantId, String restaurantName, List<MenuItemViewModel> menuItems) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.menuItems = menuItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the chef.
     *
     * @return The first name of the chef.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the chef.
     *
     * @param firstName The new first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the chef.
     *
     * @return The last name of the chef.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the chef.
     *
     * @param lastName The new last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the date of birth of the chef.
     *
     * @return The date of birth of the chef.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the chef.
     *
     * @param dateOfBirth The new date of birth to set.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the ID of the restaurant to which the chef is associated.
     *
     * @return The restaurant ID.
     */
    public long getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets the restaurant id to which the chef is associated.
     *
     * @param restaurantId The new restaurant ID to set.
     */
    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }


    /**
     * Gets the name of the restaurant to which the chef is associated.
     *
     * @return The restaurant name.
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Sets the restaurant name to which the chef is associated.
     *
     * @param restaurantName The new restaurant name to set.
     */
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }

    public List<MenuItemViewModel> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemViewModel> menuItems) {
        this.menuItems = menuItems;
    }
}
