package be.kdg.programming5.programming5.controllers.api.dto;

import be.kdg.programming5.programming5.domain.Course;
import be.kdg.programming5.programming5.domain.Restaurant;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public class MenuItemDto {
    private long id;
    private String name;
    private double price;

    public MenuItemDto() {
    }

    public MenuItemDto(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
