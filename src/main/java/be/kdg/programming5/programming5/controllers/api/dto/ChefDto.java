package be.kdg.programming5.programming5.controllers.api.dto;

/**
 * The type Chef dto.
 */
public class ChefDto {
    private long id;
    private String firstName;
    private String lastName;

    /**
     * Instantiates a new Chef dto.
     */
    public ChefDto() {
    }

    /**
     * Instantiates a new Chef dto.
     *
     * @param id        the id
     * @param firstName the first name
     * @param lastName  the last name
     */
    public ChefDto(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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
}
