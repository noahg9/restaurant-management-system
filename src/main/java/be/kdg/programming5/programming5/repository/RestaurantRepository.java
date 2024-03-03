package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.domain.Chef;
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
    @Query("""
        select restaurant from Restaurant restaurant
        left join fetch restaurant.chefs chefs
        where restaurant.id = :restaurantId
        """)
    Optional<Restaurant> findByIdWithChefs(long restaurantId);

    @Query("""
        select restaurant from Restaurant restaurant
        left join fetch restaurant.menuItems menuItems
        where restaurant.id = :restaurantId
        """)
    Optional<Restaurant> findByIdWithMenuItems(long restaurantId);
}
