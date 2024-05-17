package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.ChefRole;
import be.kdg.programming5.programming5.repository.AssignedChefRepository;
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
    private final AssignedChefRepository assignedChefRepository;

    /**
     * Instantiates a new Chef service.
     *
     * @param chefRepository         the chef repository
     * @param assignedChefRepository the assigned chef repository
     */
    public ChefService(ChefRepository chefRepository, AssignedChefRepository assignedChefRepository) {
        this.chefRepository = chefRepository;
        this.assignedChefRepository = assignedChefRepository;
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
     * Gets all chefs.
     *
     * @return the all chefs
     */
    public List<Chef> getAllChefs() {
        return chefRepository.findAll();
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
     * Gets chef by username.
     *
     * @param username the username
     * @return the chef by username
     */
    public Chef getChefByUsername(String username) {
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
     * Save chef chef.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     * @param username    the username
     * @param password    the password
     * @param role        the role
     * @return the chef
     */
    @Transactional
    public Chef saveChef(String firstName, String lastName, LocalDate dateOfBirth, String username, String password, ChefRole role) {
        return chefRepository.save(new Chef(firstName, lastName, dateOfBirth, username, password, role));
    }

    /**
     * Update chef boolean.
     *
     * @param chefId      the chef id
     * @param firstName   the first name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     * @param username    the username
     * @return the boolean
     */
    public boolean updateChef(long chefId, String firstName, String lastName, LocalDate dateOfBirth, String username) {
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

    /**
     * Delete chef boolean.
     *
     * @param chefId the chef id
     * @return the boolean
     */
    @Transactional
    public boolean deleteChef(long chefId) {
        Chef chef = chefRepository.findByIdWithMenuItems(chefId).orElse(null);
        if (chef == null) {
            return false;
        }
        assignedChefRepository.deleteAll(chef.getMenuItems());
        chefRepository.deleteById(chefId);
        return true;
    }
}
