package be.kdg.programming5.programming5.controllers.api.dto;

import be.kdg.programming5.programming5.domain.Restaurant;

import java.time.LocalDate;

public class ChefDto {
    private long id;
    private String firstName;
    private String lastName;

    public ChefDto() {
    }

    public ChefDto(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
