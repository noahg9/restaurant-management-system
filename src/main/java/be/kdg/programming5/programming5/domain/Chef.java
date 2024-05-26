package be.kdg.programming5.programming5.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Chef.
 */
@Entity
@Table(name = "chef")
public class Chef extends AbstractEntity<Long> implements Serializable {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private ChefRole role;

    @OneToMany(mappedBy = "chef", fetch = FetchType.EAGER)
    private List<MenuAssignment> menuItems;

    /**
     * Instantiates a new Chef.
     */
    public Chef() {
        setFirstName("John");
        setLastName("Doe");
        setDateOfBirth(LocalDate.of(2000, 1, 1));
        setUsername("username" + LocalDate.now());
        setPassword("password");
        setRole(ChefRole.SOUS_CHEF);
        setMenuItems(new ArrayList<>());
    }

    /**
     * Instantiates a new Chef.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     * @param username    the username
     * @param password    the password
     * @param role        the role
     */
    public Chef(String firstName, String lastName, LocalDate dateOfBirth, String username, String password, ChefRole role) {
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        setUsername(username);
        setPassword(password);
        setRole(role);
        setMenuItems(new ArrayList<>());
    }

    /**
     * Instantiates a new Chef.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     * @param username    the username
     * @param password    the password
     * @param role        the role
     * @param menuItems   the menu items
     */
    public Chef(String firstName, String lastName, LocalDate dateOfBirth, String username, String password, ChefRole role, List<MenuAssignment> menuItems) {
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        setUsername(username);
        setPassword(password);
        setRole(role);
        setMenuItems(menuItems);
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
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName.trim();
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
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.lastName = lastName.trim();
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
        Objects.requireNonNull(dateOfBirth, "Date of birth cannot be null");
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

    /**
     * Gets menu items.
     *
     * @return the menu items
     */
    public List<MenuAssignment> getMenuItems() {
        return menuItems;
    }

    /**
     * Sets menu items.
     *
     * @param menuItems the menu items
     */
    public void setMenuItems(List<MenuAssignment> menuItems) {
        this.menuItems = menuItems;
    }


    @Override
    public String toString() {
        return "Chef{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
