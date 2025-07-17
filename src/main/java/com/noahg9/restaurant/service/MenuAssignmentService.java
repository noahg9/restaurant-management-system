package com.noahg9.restaurant.service;

import com.noahg9.restaurant.domain.MenuAssignment;
import com.noahg9.restaurant.domain.Chef;
import com.noahg9.restaurant.domain.MenuItem;
import com.noahg9.restaurant.repository.MenuAssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Menu assignment service.
 */
@Service
public class MenuAssignmentService {
    private final MenuAssignmentRepository menuAssignmentRepository;

    /**
     * Instantiates a new Menu assignment service.
     *
     * @param menuAssignmentRepository the menu assignment repository
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

    /**
     * Assign chef to menu item.
     *
     * @param chef     the chef
     * @param menuItem the menu item
     */
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
