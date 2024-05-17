package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.domain.Recipe;
import be.kdg.programming5.programming5.repository.MenuItemRepository;
import be.kdg.programming5.programming5.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final MenuItemRepository menuItemRepository;

    public RecipeService(RecipeRepository recipeRepository, MenuItemRepository menuItemRepository) {
        this.recipeRepository = recipeRepository;
        this.menuItemRepository = menuItemRepository;
    }

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

    @Transactional
    public Recipe saveRecipe(String instructions, double cookingTime, int difficulty, long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElse(null);
        return recipeRepository.save(new Recipe(instructions, cookingTime, difficulty, menuItem));
    }

    @Transactional
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

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
