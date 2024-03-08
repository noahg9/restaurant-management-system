package be.kdg.programming5.programming5.controllers.api.dto;

/**
 * The type Menu item dto.
 */
public class MenuItemDto {
    private long id;
    private String name;
    private double price;

    /**
     * Instantiates a new Menu item dto.
     */
    public MenuItemDto() {
    }

    /**
     * Instantiates a new Menu item dto.
     *
     * @param id    the id
     * @param name  the name
     * @param price the price
     */
    public MenuItemDto(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
