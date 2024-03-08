package be.kdg.programming5.programming5.controllers.api.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * The type Update chef name dto.
 */
public class UpdateChefNameDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    /**
     * Instantiates a new Update chef name dto.
     */
    public UpdateChefNameDto() {
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
}
