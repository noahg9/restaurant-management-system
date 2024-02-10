package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.Restaurant;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for managing chefs.
 */
public interface ChefService {

    /**
     * Retrieves a list of all chefs.
     *
     * @return List of chefs.
     */
    List<Chef> getChefs();

    /**
     * Retrieves a chef by their ID.
     *
     * @param id The ID of the chef to retrieve.
     * @return The chef with the specified ID, or null if not found.
     */
    Chef getChef(int id);

    Chef getChefWithMenuItems(int chefId);

    /**
     * Retrieves a list of chefs by their first or last name.
     *
     * @param name The name to search for (case-insensitive).
     * @return List of chefs matching the specified name.
     */
    List<Chef> getChefsByName(String name);

    /**
     * Adds a new chef to the system.
     *
     * @param firstName    The first name of the chef.
     * @param lastName     The last name of the chef.
     * @param dateOfBirth  The date of birth of the chef.
     * @param restaurant   The restaurant where the chef works.
     * @return The newly added chef.
     */
    Chef addChef(String firstName, String lastName, LocalDate dateOfBirth, Restaurant restaurant);

    /**
     * Adds a new chef to the system.
     *
     * @param chef The chef object to add.
     * @return The newly added chef.
     */
    Chef addChef(Chef chef);

    /**
     * Updates an existing chef.
     *
     * @param chef The chef with updated information.
     */
    @Transactional
    void updateChef(Chef chef);

    /**
     * Deletes a chef by their ID.
     *
     * @param id The ID of the chef to delete.
     */
    void deleteChef(int id);
}
