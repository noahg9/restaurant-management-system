package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.Restaurant;
import be.kdg.programming5.programming5.repository.ChefRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Chef service.
 */
@Service
public class ChefService {
    private final ChefRepository chefRepository;
    private final MenuItemChefService menuItemChefService;
    private final RestaurantService restaurantService;

    /**
     * Instantiates a new Chef service.
     *
     * @param chefRepository      the chef repository
     * @param menuItemChefService the menu item chef service
     * @param restaurantService   the restaurant service
     */
    public ChefService(ChefRepository chefRepository, MenuItemChefService menuItemChefService, RestaurantService restaurantService) {
        this.chefRepository = chefRepository;
        this.menuItemChefService = menuItemChefService;
        this.restaurantService = restaurantService;
    }

    /**
     * Gets all chefs.
     *
     * @return the all chefs
     */
    public List<Chef> getAllChefs() {
        return chefRepository.findAll();
    }

    /**
     * Gets chefs with menu items.
     *
     * @return the chefs with menu items
     */
    public List<Chef> getChefsWithMenuItems() {
        return chefRepository.findAllWithMenuItems();
    }

    /**
     * Gets chef.
     *
     * @param chefId the chef id
     * @return the chef
     */
    public Chef getChef(long chefId) {
        return chefRepository.findById(chefId).orElse(null);
    }

    /**
     * Gets chef with menu items.
     *
     * @param chefId the chef id
     * @return the chef with menu items
     */
    public Chef getChefWithMenuItems(long chefId) {
        return chefRepository.findByIdWithMenuItems(chefId).orElse(null);
    }

    /**
     * Gets chefs by name.
     *
     * @param name the name
     * @return the chefs by name
     */
    public List<Chef> getChefsByName(String name) {
        return chefRepository.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(name, name);
    }

    /**
     * Gets chefs of menu item.
     *
     * @param menuItemId the menu item id
     * @return the chefs of menu item
     */
    public List<Chef> getChefsOfMenuItem(int menuItemId) {
        return chefRepository.findByMenuItemId(menuItemId);
    }

    /**
     * Search chefs by first name or last name list.
     *
     * @param searchTerm the search term
     * @return the list
     */
    public List<Chef> searchChefsByFirstNameOrLastName(String searchTerm) {
        return chefRepository.getChefsByFirstNameLikeIgnoreCaseOrLastNameIgnoreCase("%" + searchTerm + "%", "%" + searchTerm + "%");
    }

    /**
     * Add chef chef.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     * @param restaurant  the restaurant
     * @return the chef
     */
    public Chef addChef(String firstName, String lastName, LocalDate dateOfBirth, Restaurant restaurant) {
        return chefRepository.save(new Chef(firstName, lastName, dateOfBirth, restaurant));
    }

    /**
     * Add chef chef.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     * @return the chef
     */
    public Chef addChef(String firstName, String lastName, LocalDate dateOfBirth) {
        return chefRepository.save(new Chef(firstName, lastName, dateOfBirth));
    }

    /**
     * Add chef chef.
     *
     * @param chef the chef
     * @return the chef
     */
    public Chef addChef(Chef chef) {
        return chefRepository.save(chef);
    }

    /**
     * Remove chef boolean.
     *
     * @param chefId the chef id
     * @return the boolean
     */
    @Transactional
    public boolean removeChef(long chefId) {
        var chef = chefRepository.findByIdWithMenuItems(chefId);
        if (chef.isEmpty()) {
            return false;
        }
        menuItemChefService.removeAllChefs(chef.get());
        chefRepository.deleteById(chefId);
        return true;
    }

    /**
     * Change chef name boolean.
     *
     * @param chefId      the chef id
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     * @return the boolean
     */
    public boolean changeChefName(long chefId, String firstName, String lastName, LocalDate dateOfBirth) {
        var chef = chefRepository.findById(chefId).orElse(null);
        if (chef == null) {
            return false;
        }
        chef.setFirstName(firstName);
        chef.setLastName(lastName);
        chef.setDateOfBirth(dateOfBirth);
        chefRepository.save(chef);
        return true;
    }
}
