package be.kdg.programming5.programming5.model;

import java.lang.String;

/**
 * The enum Course.
 */
public enum Course {
    /**
     * Starter course.
     */
    STARTER("Starter"),
    /**
     * Main course.
     */
    MAIN("Main"),
    /**
     * Appetizer course.
     */
    APPETIZER("Appetizer"),
    /**
     * Dessert course.
     */
    DESSERT("Dessert"),
    /**
     * Beverage course.
     */
    BEVERAGE("Beverage"),
    /**
     * Other course.
     */
    OTHER("Other");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * From name course.
     *
     * @param name the name
     * @return the course
     */
    public static Course fromName(String name) {
        for (Course course : values()) {
            if (course.getName().equalsIgnoreCase(name)) {
                return course;
            }
        }
        throw new IllegalArgumentException("No Course found with name: " + name);
    }
}
