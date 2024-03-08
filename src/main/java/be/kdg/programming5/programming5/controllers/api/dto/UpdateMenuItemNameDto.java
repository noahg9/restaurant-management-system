package be.kdg.programming5.programming5.controllers.api.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * The type Update menu item name dto.
 */
public class UpdateMenuItemNameDto {
    @NotBlank
    private String name;

    /**
     * Instantiates a new Update menu item name dto.
     */
    public UpdateMenuItemNameDto() {
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
}
