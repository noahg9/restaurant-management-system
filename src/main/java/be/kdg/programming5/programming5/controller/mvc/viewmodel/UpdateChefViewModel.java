package be.kdg.programming5.programming5.controller.mvc.viewmodel;

import java.time.LocalDate;

public class UpdateChefViewModel {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String username;

    public UpdateChefViewModel() {
    }

    public UpdateChefViewModel(long id, String firstName, String lastName, LocalDate dateOfBirth, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
