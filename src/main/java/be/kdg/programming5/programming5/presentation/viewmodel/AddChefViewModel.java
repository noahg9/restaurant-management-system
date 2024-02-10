package be.kdg.programming5.programming5.presentation.viewmodel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * ViewModel class for adding a Chef, containing necessary information.
 */
public class AddChefViewModel {

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @NotNull(message = "Date of birth cannot be null")
    private LocalDate dateOfBirth;

    @NotNull(message = "Restaurant ID cannot be null")
    private Integer restaurantId;

    /**
     * Default constructor for AddChefViewModel.
     */
    public AddChefViewModel() {}

    /**
     * Parameterized constructor for AddChefViewModel.
     *
     * @param firstName    The first name of the chef.
     * @param lastName     The last name of the chef.
     * @param dateOfBirth  The date of birth of the chef.
     * @param restaurantId The ID of the restaurant to which the chef is associated.
     */
    public AddChefViewModel(String firstName, String lastName, LocalDate dateOfBirth, int restaurantId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.restaurantId = restaurantId;
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
    public int getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets the ID of the restaurant to which the chef is associated.
     *
     * @param restaurantId The new restaurant ID to set.
     */
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Overrides the toString method to provide a string representation of the AddChefViewModel object.
     *
     * @return A string representation of the AddChefViewModel object.
     */
    @Override
    public String toString() {
        return "AddChefViewModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
