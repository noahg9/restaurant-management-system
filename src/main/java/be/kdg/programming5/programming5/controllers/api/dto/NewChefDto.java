package be.kdg.programming5.programming5.controllers.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

/**
 * The type New chef dto.
 */
public class NewChefDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private LocalDate dateOfBirth;

    /**
     * Instantiates a new New chef dto.
     */
    public NewChefDto() {
    }

    /**
     * Instantiates a new New chef dto.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     */
    public NewChefDto(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Instantiates a new New chef dto.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    public NewChefDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

}
