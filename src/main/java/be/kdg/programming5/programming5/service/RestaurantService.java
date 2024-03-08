package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.Restaurant;
import be.kdg.programming5.programming5.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service implementation using Spring Data JPA for Restaurant-related operations.
 */
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    /**
     * Constructs a SpringDataRestaurantService with the specified repository.
     *
     * @param restaurantRepository The repository for Restaurant entities.
     */
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Retrieves all chefs of all restaurants.
     *
     * @return All chefs of all restaurants.
     */
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    /**
     * Retrieves a restaurant by its identifier.
     *
     * @param id The identifier of the restaurant.
     * @return The restaurant with the specified identifier, or null if not found.
     */
    public Restaurant getRestaurant(long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    /**
     * Gets restaurant by name.
     *
     * @param name the name
     * @return the restaurant by name
     */
    public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }


    /**
     * Retrieves a restaurant with its associated chefs.
     *
     * @param restaurantId The identifier of the restaurant.
     * @return The restaurant with the specified identifier, or null if not found.
     */
    public Restaurant getRestaurantWithChefs(long restaurantId) {
        return restaurantRepository.findByIdWithChefs(restaurantId).orElse(null);
    }

    /**
     * Retrieves a restaurant with its associated menu items.
     *
     * @param restaurantId The identifier of the restaurant.
     * @return The restaurant with the specified identifier, or null if not found.
     */
    public Restaurant getRestaurantWithMenuItems(long restaurantId) {
        return restaurantRepository.findByIdWithMenuItems(restaurantId).orElse(null);
    }


    /**
     * Adds a new restaurant with the specified details.
     *
     * @param name            The name of the restaurant.
     * @param dateEstablished The date the restaurant was established.
     * @param seatingCapacity The seating capacity of the restaurant.
     * @return The newly created restaurant.
     */
    public Restaurant addRestaurant(String name, LocalDate dateEstablished, int seatingCapacity) {
        return restaurantRepository.save(new Restaurant(name, dateEstablished, seatingCapacity));
    }

    /**
     * Adds a restaurant to the system.
     *
     * @param restaurant The restaurant to add.
     * @return The added restaurant.
     */
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    /**
     * Deletes a restaurant by its identifier.
     *
     * @param id The identifier of the restaurant to delete.
     */
    @Transactional
    public void removeRestaurant(long id) {
        restaurantRepository.deleteById(id);
    }
}
