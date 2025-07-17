package com.noahg9.restaurant.repository;

import com.noahg9.restaurant.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Recipe repository.
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
