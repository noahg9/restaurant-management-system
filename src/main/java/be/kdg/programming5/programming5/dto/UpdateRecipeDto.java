package be.kdg.programming5.programming5.dto;

/**
 * The type Recipe dto.
 */
public class UpdateRecipeDto {
    private String instructions;
    private double cookingTime;
    private int difficulty;

    /**
     * Instantiates a new Recipe dto.
     */
    public UpdateRecipeDto() {
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
