package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.Restaurant;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for managing restaurants.
 */
public interface RestaurantService {

    /**
     * Retrieves a list of all restaurants.
     *
     * @return List of restaurants.
     */
    List<Restaurant> getRestaurants();

    /**
     * Retrieves a restaurant by its ID.
     *
     * @param id The ID of the restaurant to retrieve.
     * @return The restaurant with the specified ID, or null if not found.
     */
    Restaurant getRestaurant(int id);

    /**
     * Adds a new restaurant to the system.
     *
     * @param name             The name of the restaurant.
     * @param dateEstablished The date the restaurant was established.
     * @param seatingCapacity The seating capacity of the restaurant.
     * @return The newly added restaurant.
     */
    Restaurant addRestaurant(String name, LocalDate dateEstablished, int seatingCapacity);

    /**
     * Adds a new restaurant to the system.
     *
     * @param restaurant The restaurant object to add.
     * @return The newly added restaurant.
     */
    Restaurant addRestaurant(Restaurant restaurant);

    /**
     * Updates an existing restaurant.
     *
     * @param restaurant The restaurant with updated information.
     */
    @Transactional
    void updateRestaurant(Restaurant restaurant);

    /**
     * Deletes a restaurant by its ID.
     *
     * @param id The ID of the restaurant to delete.
     */
    void deleteRestaurant(int id);
}
