package be.kdg.programming5.programming5.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The type Recipe.
 */
@Entity
@Table(name = "recipe")
public class Recipe extends AbstractEntity<Long> implements Serializable {

    @Column(nullable = false)
    private String instructions;

    @Column(nullable = false)
    private double cookingTime;

    @Column(nullable = false)
    private int difficulty;

    @OneToOne
    @JoinColumn(name = "menu_item_id", referencedColumnName = "id")
    private MenuItem menuItem;

    /**
     * Instantiates a new Recipe.
     */
    public Recipe() {
        setInstructions("");
        setCookingTime(0);
        setDifficulty(0);
    }

    /**
     * Instantiates a new Recipe.
     *
     * @param instructions the instructions
     * @param cookingTime  the cooking time
     * @param difficulty   the difficulty
     */
    public Recipe(String instructions, double cookingTime, int difficulty) {
        setInstructions(instructions);
        setCookingTime(cookingTime);
        setDifficulty(difficulty);
    }

    /**
     * Instantiates a new Recipe.
     *
     * @param instructions the instructions
     * @param cookingTime  the cooking time
     * @param difficulty   the difficulty
     * @param menuItem     the menu item
     */
    public Recipe(String instructions, double cookingTime, int difficulty, MenuItem menuItem) {
        setInstructions(instructions);
        setCookingTime(cookingTime);
        setDifficulty(difficulty);
        setMenuItem(menuItem);
    }

    // Getters and setters

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

    /**
     * Gets menu item.
     *
     * @return the menu item
     */
    public MenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * Sets menu item.
     *
     * @param menuItem the menu item
     */
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        menuItem.setRecipe(this);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "instructions='" + instructions + '\'' +
                ", cookingTime=" + cookingTime +
                ", difficulty=" + difficulty +
                ", menuItem=" + menuItem.getName() +
                '}';
    }
}
