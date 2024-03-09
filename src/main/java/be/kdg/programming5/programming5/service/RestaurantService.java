package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.Restaurant;
import be.kdg.programming5.programming5.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Restaurant service.
 */
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    /**
     * Instantiates a new Restaurant service.
     *
     * @param restaurantRepository the restaurant repository
     */
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Gets all restaurants.
     *
     * @return the all restaurants
     */
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    /**
     * Gets restaurant.
     *
     * @param id the id
     * @return the restaurant
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
     * Gets restaurant with chefs.
     *
     * @param restaurantId the restaurant id
     * @return the restaurant with chefs
     */
    public Restaurant getRestaurantWithChefs(long restaurantId) {
        return restaurantRepository.findByIdWithChefs(restaurantId).orElse(null);
    }

    /**
     * Gets restaurant with menu items.
     *
     * @param restaurantId the restaurant id
     * @return the restaurant with menu items
     */
    public Restaurant getRestaurantWithMenuItems(long restaurantId) {
        return restaurantRepository.findByIdWithMenuItems(restaurantId).orElse(null);
    }


    /**
     * Add restaurant restaurant.
     *
     * @param name            the name
     * @param dateEstablished the date established
     * @param seatingCapacity the seating capacity
     * @return the restaurant
     */
    public Restaurant addRestaurant(String name, LocalDate dateEstablished, int seatingCapacity) {
        return restaurantRepository.save(new Restaurant(name, dateEstablished, seatingCapacity));
    }

    /**
     * Add restaurant restaurant.
     *
     * @param restaurant the restaurant
     * @return the restaurant
     */
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    /**
     * Remove restaurant.
     *
     * @param id the id
     */
    @Transactional
    public void removeRestaurant(long id) {
        restaurantRepository.deleteById(id);
    }
}
