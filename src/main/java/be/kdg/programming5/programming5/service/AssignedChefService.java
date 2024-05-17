package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.AssignedChef;
import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.repository.AssignedChefRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void assignChefToMenuItem(Chef chef, MenuItem menuItem) {
        // Check if the chef is already assigned to the menu item
        if (assignedChefRepository.existsByChefAndMenuItem(chef, menuItem)) {
            throw new IllegalArgumentException("Chef is already assigned to this menu item");
        }

        // Create the association
        AssignedChef assignedChef = new AssignedChef();
        assignedChef.setChef(chef);
        assignedChef.setMenuItem(menuItem);

        // Save the association
        assignedChefRepository.save(assignedChef);
    }
}
