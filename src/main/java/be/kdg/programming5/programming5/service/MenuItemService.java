package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.Course;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.domain.Restaurant;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * Service interface for managing menu items.
 */
public interface MenuItemService {

    /**
     * Retrieves a list of all menu items.
     *
     * @return List of menu items.
     */
    List<MenuItem> getMenuItems();

    /**
     * Retrieves a menu item by its ID.
     *
     * @param id The ID of the menu item to retrieve.
     * @return The menu item with the specified ID, or null if not found.
     */
    MenuItem getMenuItemById(int id);

    /**
     * Retrieves a list of menu items with a price less than the specified maximum price.
     *
     * @param maxPrice The maximum price to filter menu items.
     * @return List of menu items within the specified price range.
     */
    List<MenuItem> getMenuItemsByMaxPrice(double maxPrice);

    /**
     * Retrieves a list of vegetarian menu items.
     *
     * @return List of vegetarian menu items.
     */
    List<MenuItem> getVegMenuItems();

    /**
     * Adds a new menu item to the system.
     *
     * @param name       The name of the menu item.
     * @param price      The price of the menu item.
     * @param course     The course category of the menu item.
     * @param vegetarian True if the menu item is vegetarian, false otherwise.
     * @param spiceLvl   The spice level of the menu item.
     * @param restaurant The restaurant offering the menu item.
     * @return The newly added menu item.
     */
    MenuItem addMenuItem(String name, double price, Course course, Boolean vegetarian, int spiceLvl, Restaurant restaurant);

    /**
     * Adds a new menu item to the system.
     *
     * @param menuItem The menu item object to add.
     * @return The newly added menu item.
     */
    MenuItem addMenuItem(MenuItem menuItem);

    /**
     * Updates an existing menu item.
     *
     * @param menuItem The menu item with updated information.
     */
    @Transactional
    void updateMenuItem(MenuItem menuItem);

    /**
     * Deletes a menu item by its ID.
     *
     * @param id The ID of the menu item to delete.
     */
    void deleteMenuItem(int id);
}
