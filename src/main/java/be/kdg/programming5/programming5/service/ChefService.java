package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.domain.Restaurant;
import be.kdg.programming5.programming5.repository.ChefRepository;
import be.kdg.programming5.programming5.repository.MenuItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service implementation using Spring Data JPA for Chef-related operations.
 */
@Service
public class ChefService {
    private final ChefRepository chefRepository;
    private final MenuItemRepository menuItemRepository;

    /**
     * Constructs a SpringDataChefService with the specified repositories.
     *
     * @param chefRepository      The repository for Chef entities.
     * @param menuItemRepository The repository for MenuItem entities.
     */
    public ChefService(ChefRepository chefRepository, MenuItemRepository menuItemRepository) {
        this.chefRepository = chefRepository;
        this.menuItemRepository = menuItemRepository;
    }

    /**
     * Retrieves a list of all chefs.
     *
     * @return A list of all chefs.
     */
    
    public List<Chef> getChefs() {
        return chefRepository.findAll();
    }

    /**
     * Retrieves a list of all chefs with its associated menu items.
     *
     * @return A list of all chefs.
     */
    
    public List<Chef> getChefsWithMenuItems() {
        return chefRepository.findAllWithMenuItems();
    }

    /**
     * Retrieves a chef by its identifier.
     *
     * @param id The identifier of the chef.
     * @return The chef with the specified identifier, or null if not found.
     */
    
    public Chef getChef(int id) {
        return chefRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves a chef with its associated menu items.
     *
     * @param id The identifier of the chef.
     * @return The chef with the specified identifier, or null if not found.
     */
    
    public Chef getChefWithMenuItems(int id) {
        return chefRepository.findByIdWithMenuItems(id).orElse(null);
    }

    /**
     * Retrieves a list of chefs by their first or last name, case-insensitive.
     *
     * @param name The name to search for.
     * @return A list of chefs matching the search criteria.
     */
    
    public List<Chef> getChefsByName(String name) {
        return chefRepository.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(name, name);
    }

    public List<Chef> getChefsOfMenuItem(int menuItemId) {
        return chefRepository.findByMenuItemId(menuItemId);
    }

    public List<Chef> searchChefsByNameLike(String searchTerm) {
        return chefRepository.findChefsByNameLike(searchTerm);
    }

    /**
     * Adds a new chef with the specified details.
     *
     * @param firstName    The first name of the chef.
     * @param lastName     The last name of the chef.
     * @param dateOfBirth  The date of birth of the chef.
     * @param restaurant   The restaurant where the chef works.
     * @return The newly created chef.
     */
    
    @Transactional
    public Chef addChef(String firstName, String lastName, LocalDate dateOfBirth, Restaurant restaurant) {
        return chefRepository.save(new Chef(firstName, lastName, dateOfBirth, restaurant));
    }

    /**
     * Adds a chef to the system.
     *
     * @param chef The chef to add.
     * @return The added chef.
     */
    
    @Transactional
    public Chef addChef(Chef chef) {
        return chefRepository.save(chef);
    }

    /**
     * Updates a chef's information.
     *
     * @param chef The chef to update.
     */
    
    @Transactional
    public void updateChef(Chef chef) {
        // Implementation needed based on the specific requirements.
    }

    /**
     * Deletes a chef by its identifier.
     *
     * @param id The identifier of the chef to delete.
     */
    
    @Transactional
    public void deleteChef(int id) {
/*        Chef chef = getChef(id);
        if (chef != null) {
            chef.getMenuItems().forEach(menuItem -> {
                menuItem.getChefs().remove(chef);
                menuItemRepository.save(menuItem);
            });
            chefRepository.deleteById(id);
        }*/
    }
}
