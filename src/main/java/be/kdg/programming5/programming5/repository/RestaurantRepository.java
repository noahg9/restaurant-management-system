package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for accessing Restaurant entities.
 * Provides methods to interact with Restaurant data stored in a database.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
