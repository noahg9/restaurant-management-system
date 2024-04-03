package be.kdg.programming5.programming5.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

/**
 * The type New chef dto.
 */
public class NewChefDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private LocalDate dateOfBirth;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String roleName;

    private List<Long> menuItemIds;

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
     * @param username    the username
     * @param password    the password
     * @param roleName        the role
     */
    public NewChefDto(String firstName, String lastName, LocalDate dateOfBirth, String username, String password, String roleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.roleName = roleName;
    }

    public NewChefDto(String firstName, String lastName, LocalDate dateOfBirth, String username, String password, String roleName, List<Long> menuItemIds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.roleName = roleName;
        this.menuItemIds = menuItemIds;
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

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets role.
     *
     * @param roleName the role
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Long> getMenuItemIds() {
        return menuItemIds;
    }

    public void setMenuItemIds(List<Long> menuItemIds) {
        this.menuItemIds = menuItemIds;
    }
}
