package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.model.Chef;
import be.kdg.programming5.programming5.model.MenuItem;
import be.kdg.programming5.programming5.repository.AssignedChefRepository;
import org.springframework.stereotype.Service;

/**
 * The type Menu item chef service.
 */
@Service
public class AssignedChefService {
    private final AssignedChefRepository assignedChefRepository;

    /**
     * Instantiates a new Menu item chef service.
     *
     * @param assignedChefRepository the menu item chef repository
     */
    public AssignedChefService(AssignedChefRepository assignedChefRepository) {
        this.assignedChefRepository = assignedChefRepository;
    }

    /**
     * Is chef assigned to menu item boolean.
     *
     * @param menuItemId the menu item id
     * @param chefId     the chef id
     * @return the boolean
     */
    public boolean isChefAssignedToMenuItem(long menuItemId, long chefId) {
        return assignedChefRepository
                .findByMenuItemIdAndChefId(menuItemId, chefId)
                .isPresent();
    }
}
