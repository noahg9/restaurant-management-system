package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data repository for accessing Restaurant entities.
 * Provides methods to interact with Restaurant data stored in a database.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    /**
     * Find by name restaurant.
     *
     * @param name the name
     * @return the restaurant
     */
    Restaurant findByName(String name);


    /**
     * Find by id with chefs optional.
     *
     * @param restaurantId the restaurant id
     * @return the optional
     */
    @Query("""
        select distinct r from Restaurant r
        join Chef c on c.restaurant.id = r.id
        where c.restaurant.id = :restaurantId
        """)
    Optional<Restaurant> findByIdWithChefs(long restaurantId);

    /**
     * Find by id with menu items optional.
     *
     * @param restaurantId the restaurant id
     * @return the optional
     */
    @Query("""
        select r from Restaurant r
        join MenuItem m on m.restaurant.id = r.id
        where r.id = :restaurantId
        """)
    Optional<Restaurant> findByIdWithMenuItems(long restaurantId);
}
