package be.kdg.programming5.programming5.controller.web.viewmodel;

import be.kdg.programming5.programming5.model.ChefRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Chef view model.
 */
public class ChefViewModel {
    private long id;
    @NotBlank(message = "First name cannot be empty")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;
    @NotNull(message = "Date of birth cannot be null")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @NotNull(message = "Role cannot be null")
    private ChefRole role;
    private boolean modificationAllowed;
    private List<MenuItemViewModel> menuItems;

    /**
     * Instantiates a new Chef view model.
     */
    public ChefViewModel() {
    }

    /**
     * Instantiates a new Chef view model.
     *
     * @param id                  the id
     * @param firstName           the first name
     * @param lastName            the last name
     * @param dateOfBirth         the date of birth
     * @param username            the username
     * @param password            the password
     * @param role                the role
     * @param modificationAllowed the modification allowed
     * @param menuItems           the menu items
     */
    public ChefViewModel(long id, String firstName, String lastName, LocalDate dateOfBirth, String username, String password, ChefRole role, boolean modificationAllowed, List<MenuItemViewModel> menuItems) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.role = role;
        this.modificationAllowed = modificationAllowed;
        this.menuItems = menuItems;
    }

    /**
     * Instantiates a new Chef view model.
     *
     * @param id                  the id
     * @param firstName           the first name
     * @param lastName            the last name
     * @param dateOfBirth         the date of birth
     * @param username            the username
     * @param password            the password
     * @param role                the role
     * @param modificationAllowed the modification allowed
     */
    public ChefViewModel(long id, String firstName, String lastName, LocalDate dateOfBirth, String username, String password, ChefRole role, boolean modificationAllowed) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.role = role;
        this.modificationAllowed = modificationAllowed;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
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
    public ChefRole getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(ChefRole role) {
        this.role = role;
    }

    public boolean isModificationAllowed() {
        return modificationAllowed;
    }

    public void setModificationAllowed(boolean modificationAllowed) {
        this.modificationAllowed = modificationAllowed;
    }

    public List<MenuItemViewModel> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemViewModel> menuItems) {
        this.menuItems = menuItems;
    }
}
