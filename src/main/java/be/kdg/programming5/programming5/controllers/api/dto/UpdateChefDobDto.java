package be.kdg.programming5.programming5.controllers.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class UpdateChefDobDto {
    @NotBlank
    private LocalDate dateOfBirth;

    public UpdateChefDobDto() {
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
