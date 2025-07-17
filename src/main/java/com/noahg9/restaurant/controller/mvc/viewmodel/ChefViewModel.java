package com.noahg9.restaurant.controller.mvc.viewmodel;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Chef view model.
 */
public class ChefViewModel {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String username;
    private String roleName;
    private boolean modificationAllowed;
    private List<MenuItemViewModel> menuItems;

    /**
     * Instantiates a new Chef view model.
     *
     * @param id                  the id
     * @param firstName           the first name
     * @param lastName            the last name
     * @param dateOfBirth         the date of birth
     * @param username            the username
     * @param roleName            the role name
     * @param modificationAllowed the modification allowed
     */
    public ChefViewModel(long id, String firstName, String lastName, LocalDate dateOfBirth, String username, String roleName, boolean modificationAllowed) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.roleName = roleName;
        this.modificationAllowed = modificationAllowed;
    }

    /**
     * Instantiates a new Chef view model.
     *
     * @param id                  the id
     * @param firstName           the first name
     * @param lastName            the last name
     * @param dateOfBirth         the date of birth
     * @param username            the username
     * @param roleName            the role name
     * @param modificationAllowed the modification allowed
     * @param menuItems           the menu items
     */
    public ChefViewModel(long id, String firstName, String lastName, LocalDate dateOfBirth, String username, String roleName, boolean modificationAllowed, List<MenuItemViewModel> menuItems) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.roleName = roleName;
        this.modificationAllowed = modificationAllowed;
        this.menuItems = menuItems;
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
     * Gets role name.
     *
     * @return the role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets role name.
     *
     * @param roleName the role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Is modification allowed boolean.
     *
     * @return the boolean
     */
    public boolean isModificationAllowed() {
        return modificationAllowed;
    }

    /**
     * Sets modification allowed.
     *
     * @param modificationAllowed the modification allowed
     */
    public void setModificationAllowed(boolean modificationAllowed) {
        this.modificationAllowed = modificationAllowed;
    }

    /**
     * Gets menu items.
     *
     * @return the menu items
     */
    public List<MenuItemViewModel> getMenuItems() {
        return menuItems;
    }

    /**
     * Sets menu items.
     *
     * @param menuItems the menu items
     */
    public void setMenuItems(List<MenuItemViewModel> menuItems) {
        this.menuItems = menuItems;
    }
}
