package be.kdg.programming5.programming5.controllers.api.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateMenuItemNameDto {
    @NotBlank
    private String name;

    public UpdateMenuItemNameDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
