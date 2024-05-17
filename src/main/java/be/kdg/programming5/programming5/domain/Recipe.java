package be.kdg.programming5.programming5.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.io.Serializable;

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

    public Recipe() {
        setInstructions("");
        setCookingTime(0);
        setDifficulty(0);
    }

    public Recipe(String instructions, double cookingTime, int difficulty) {
        setInstructions(instructions);
        setCookingTime(cookingTime);
        setDifficulty(difficulty);
    }

    public Recipe(String instructions, double cookingTime, int difficulty, MenuItem menuItem) {
        setInstructions(instructions);
        setCookingTime(cookingTime);
        setDifficulty(difficulty);
        setMenuItem(menuItem);
    }

    // Getters and setters

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

    public MenuItem getMenuItem() {
        return menuItem;
    }

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
