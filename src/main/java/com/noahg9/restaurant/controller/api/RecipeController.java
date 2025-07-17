package com.noahg9.restaurant.controller.api;

import com.noahg9.restaurant.dto.RecipeDto;
import com.noahg9.restaurant.dto.UpdateRecipeDto;
import com.noahg9.restaurant.domain.Recipe;
import com.noahg9.restaurant.service.RecipeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * The type Recipe controller.
 */
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    private final RecipeService recipeService;
    private final ModelMapper modelMapper;

    /**
     * Instantiates a new Recipe controller.
     *
     * @param recipeService the recipe service
     * @param modelMapper   the model mapper
     */
    public RecipeController(RecipeService recipeService, ModelMapper modelMapper) {
        this.recipeService = recipeService;
        this.modelMapper = modelMapper;
    }

    /**
     * Gets recipe.
     *
     * @param recipeId the recipe id
     * @return the recipe
     */
    @GetMapping("{id}")
    ResponseEntity<RecipeDto> getRecipe(@PathVariable("id") long recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if (recipe == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(recipe, RecipeDto.class));
    }

    /**
     * Gets all recipes.
     *
     * @return the all recipes
     */
    @GetMapping
    ResponseEntity<List<RecipeDto>> getAllRecipes() {
        List<Recipe> allRecipes = recipeService.getAllRecipes();
        if (allRecipes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<RecipeDto> recipeDtos = allRecipes.stream().map(recipe -> modelMapper.map(recipe, RecipeDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(recipeDtos);
        }
    }

    /**
     * Update recipe response entity.
     *
     * @param recipeId        the recipe id
     * @param updateRecipeDto the update recipe dto
     * @return the response entity
     */
    @PatchMapping("{id}")
    ResponseEntity<Void> updateRecipe(@PathVariable("id") long recipeId, @RequestBody @Valid UpdateRecipeDto updateRecipeDto) {
        if (recipeService.updateRecipe(recipeId, updateRecipeDto.getInstructions(), updateRecipeDto.getCookingTime(), updateRecipeDto.getDifficulty())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
