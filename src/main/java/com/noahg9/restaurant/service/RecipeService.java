package com.noahg9.restaurant.service;

import com.noahg9.restaurant.domain.MenuItem;
import com.noahg9.restaurant.domain.Recipe;
import com.noahg9.restaurant.repository.MenuItemRepository;
import com.noahg9.restaurant.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Recipe service.
 */
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final MenuItemRepository menuItemRepository;

    /**
     * Instantiates a new Recipe service.
     *
     * @param recipeRepository   the recipe repository
     * @param menuItemRepository the menu item repository
     */
    public RecipeService(RecipeRepository recipeRepository, MenuItemRepository menuItemRepository) {
        this.recipeRepository = recipeRepository;
        this.menuItemRepository = menuItemRepository;
    }

    /**
     * Gets recipe.
     *
     * @param recipeId the recipe id
     * @return the recipe
     */
    public Recipe getRecipe(long recipeId) {
        return recipeRepository.findById(recipeId).orElse(null);
    }

    /**
     * Gets all recipes.
     *
     * @return the all recipes
     */
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    /**
     * Save recipe recipe.
     *
     * @param instructions the instructions
     * @param cookingTime  the cooking time
     * @param difficulty   the difficulty
     * @param menuItemId   the menu item id
     * @return the recipe
     */
    @Transactional
    public Recipe saveRecipe(String instructions, double cookingTime, int difficulty, long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElse(null);
        return recipeRepository.save(new Recipe(instructions, cookingTime, difficulty, menuItem));
    }

    /**
     * Save recipe recipe.
     *
     * @param recipe the recipe
     * @return the recipe
     */
    @Transactional
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    /**
     * Update recipe boolean.
     *
     * @param recipeId     the recipe id
     * @param instructions the instructions
     * @param cookingTime  the cooking time
     * @param difficulty   the difficulty
     * @return the boolean
     */
    public boolean updateRecipe(long recipeId, String instructions, double cookingTime, int difficulty) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe == null) {
            return false;
        }
        recipe.setInstructions(instructions);
        recipe.setCookingTime(cookingTime);
        recipe.setDifficulty(difficulty);
        recipeRepository.save(recipe);
        return true;
    }
}
