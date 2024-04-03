package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Menu item repository.
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
