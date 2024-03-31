package be.kdg.programming5.programming5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "recipe")
public class Recipe extends AbstractEntity<Long> implements Serializable {

    @Column(nullable = false)
    private String instructions;

    @Column(nullable = false)
    private int cookingTime;

    @Column(nullable = false)
    private int difficulty;

    @OneToOne
    @JoinColumn(name = "menu_item_id", referencedColumnName = "id")
    private MenuItem menuItem;

    public Recipe() {
    }

    public Recipe(String instructions, int cookingTime, int difficulty) {
        this.instructions = instructions;
        this.cookingTime = cookingTime;
        this.difficulty = difficulty;
    }

    public Recipe(String instructions, int cookingTime, int difficulty, MenuItem menuItem) {
        this.instructions = instructions;
        this.cookingTime = cookingTime;
        this.difficulty = difficulty;
        this.menuItem = menuItem;
    }

    // Getters and setters

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        menuItem.setRecipe(this);
    }
}
