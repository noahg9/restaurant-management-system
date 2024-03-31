package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.model.Chef;
import be.kdg.programming5.programming5.model.ChefRole;
import be.kdg.programming5.programming5.repository.ChefRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The type Chef service.
 */
@Service
public class ChefService {
    private final ChefRepository chefRepository;
    private final AssignedChefService assignedChefService;

    /**
     * Instantiates a new Chef service.
     *
     * @param chefRepository      the chef repository
     * @param assignedChefService the menu item chef service
     */
    public ChefService(ChefRepository chefRepository, AssignedChefService assignedChefService) {
        this.chefRepository = chefRepository;
        this.assignedChefService = assignedChefService;
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
     * Gets chef by name.
     *
     * @param username the username
     * @return the chef by name
     */
    public Chef getChefByName(String username) {
        return chefRepository.findByUsername(username).orElse(null);
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
     * @param username    the username
     * @param password    the password
     * @param role        the role
     * @return the chef
     */
    public Chef addChef(String firstName, String lastName, LocalDate dateOfBirth, String username, String password, ChefRole role) {
        return chefRepository.save(new Chef(firstName, lastName, dateOfBirth, username, password, role));
    }

    /**
     * Remove chef boolean.
     *
     * @param chefId the chef id
     * @return the boolean
     */
    @Transactional
    public boolean removeChef(long chefId) {
        Optional<Chef> chef = chefRepository.findByIdWithMenuItems(chefId);
        if (chef.isEmpty()) {
            return false;
        }
        assignedChefService.removeAllChefs(chef.get());
        chefRepository.deleteById(chefId);
        return true;
    }

    /**
     * Change chef boolean.
     *
     * @param chefId      the chef id
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     * @param username    the username
     * @return the boolean
     */
    public boolean changeChef(long chefId, String firstName, String lastName, LocalDate dateOfBirth, String username) {
        Chef chef = chefRepository.findById(chefId).orElse(null);
        if (chef == null) {
            return false;
        }
        chef.setFirstName(firstName);
        chef.setLastName(lastName);
        chef.setDateOfBirth(dateOfBirth);
        chef.setUsername(username);
        chefRepository.save(chef);
        return true;
    }
}
