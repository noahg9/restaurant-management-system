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
     * Retrieves a all_chefs of all restaurants.
     *
     * @return A all_chefs of all restaurants.
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
    
    public Restaurant getRestaurant(int id) {
        return restaurantRepository.findById(id).orElse(null);
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
     * Updates a restaurant's information.
     *
     * @param restaurant The restaurant to update.
     */
    
    public void updateRestaurant(Restaurant restaurant) {
        // Implementation needed based on the specific requirements.
    }

    /**
     * Deletes a restaurant by its identifier.
     *
     * @param id The identifier of the restaurant to delete.
     */
    
    @Transactional
    public void deleteRestaurant(int id) {
        restaurantRepository.deleteById(id);
    }
}
