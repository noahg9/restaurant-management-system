package be.kdg.programming5.programming5.controllers.api.dto;

import be.kdg.programming5.programming5.domain.Course;
import be.kdg.programming5.programming5.domain.Restaurant;
import jakarta.validation.constraints.NotBlank;

public class NewMenuItemDto {
    @NotBlank
    private String name;
    private double price;
    private Course course;
    private boolean vegetarian;
    private int spiceLvl;

    public NewMenuItemDto() {
    }

    public NewMenuItemDto(String name, double price, Course course, boolean vegetarian, int spiceLvl) {
        this.name = name;
        this.price = price;
        this.course = course;
        this.vegetarian = vegetarian;
        this.spiceLvl = spiceLvl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getSpiceLvl() {
        return spiceLvl;
    }

    public void setSpiceLvl(int spiceLvl) {
        this.spiceLvl = spiceLvl;
    }
}
