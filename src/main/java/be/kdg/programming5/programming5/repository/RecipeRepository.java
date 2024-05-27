package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Recipe repository.
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
