package be.kdg.programming5.programming5.controllers.api.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateChefNameDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    public UpdateChefNameDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
