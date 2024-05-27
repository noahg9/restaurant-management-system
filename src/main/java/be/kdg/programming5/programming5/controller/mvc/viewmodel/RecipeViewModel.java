package be.kdg.programming5.programming5.controller.mvc.viewmodel;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * The type Recipe view model.
 */
public class RecipeViewModel {
    private long id;
    private String instructions;
    private double cookingTime;
    private int difficulty;

    /**
     * Instantiates a new Recipe view model.
     *
     * @param id           the id
     * @param instructions the instructions
     * @param cookingTime  the cooking time
     * @param difficulty   the difficulty
     */
    public RecipeViewModel(long id, String instructions, double cookingTime, int difficulty) {
        this.id = id;
        this.instructions = instructions;
        this.cookingTime = cookingTime;
        this.difficulty = difficulty;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets instructions.
     *
     * @return the instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Sets instructions.
     *
     * @param instructions the instructions
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * Gets cooking time.
     *
     * @return the cooking time
     */
    public double getCookingTime() {
        return cookingTime;
    }

    /**
     * Sets cooking time.
     *
     * @param cookingTime the cooking time
     */
    public void setCookingTime(double cookingTime) {
        this.cookingTime = cookingTime;
    }

    /**
     * Gets difficulty.
     *
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Sets difficulty.
     *
     * @param difficulty the difficulty
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}


