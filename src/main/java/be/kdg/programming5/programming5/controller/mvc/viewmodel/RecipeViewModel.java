package be.kdg.programming5.programming5.controller.mvc.viewmodel;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * The type Menu item view model.
 */
public class RecipeViewModel {
    private long id;
    private String instructions;
    private double cookingTime;
    private int difficulty;

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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public double getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(double cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}


