package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.MenuAssignment;
import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.repository.MenuAssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Menu item chef service.
 */
@Service
public class MenuAssignmentService {
    private final MenuAssignmentRepository menuAssignmentRepository;

    /**
     * Instantiates a new Menu item chef service.
     *
     * @param menuAssignmentRepository the menu item chef repository
     */
    public MenuAssignmentService(MenuAssignmentRepository menuAssignmentRepository) {
        this.menuAssignmentRepository = menuAssignmentRepository;
    }

    /**
     * Is chef assigned to menu item boolean.
     *
     * @param menuItemId the menu item id
     * @param chefId     the chef id
     * @return the boolean
     */
    public boolean isChefAssignedToMenuItem(long menuItemId, long chefId) {
        return menuAssignmentRepository
                .findByMenuItemIdAndChefId(menuItemId, chefId)
                .isPresent();
    }

    @Transactional
    public void assignChefToMenuItem(Chef chef, MenuItem menuItem) {
        // Check if the chef is already assigned to the menu item
        if (menuAssignmentRepository.existsByChefAndMenuItem(chef, menuItem)) {
            throw new IllegalArgumentException("Chef is already assigned to this menu item");
        }

        // Create the association
        MenuAssignment menuAssignment = new MenuAssignment();
        menuAssignment.setChef(chef);
        menuAssignment.setMenuItem(menuItem);

        // Save the association
        menuAssignmentRepository.save(menuAssignment);
    }
}
