package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.repository.MenuItemChefRepository;
import org.springframework.stereotype.Service;

/**
 * The type Menu item chef service.
 */
@Service
public class MenuItemChefService {
    private final MenuItemChefRepository menuItemChefRepository;

    /**
     * Instantiates a new Menu item chef service.
     *
     * @param menuItemChefRepository the menu item chef repository
     */
    public MenuItemChefService(MenuItemChefRepository menuItemChefRepository) {
        this.menuItemChefRepository = menuItemChefRepository;
    }

    /**
     * Is chef assigned to menu item boolean.
     *
     * @param menuItemId the menu item id
     * @param chefId     the chef id
     * @return the boolean
     */
    public boolean isChefAssignedToMenuItem(long menuItemId, long chefId) {
        return menuItemChefRepository
                .findByMenuItemIdAndChefId(menuItemId, chefId)
                .isPresent();
    }

    /**
     * Remove all chefs.
     *
     * @param chef the chef
     */
    public void removeAllChefs(Chef chef) {
        menuItemChefRepository.deleteAll(chef.getMenuItems());
    }

    /**
     * Remove all menu items.
     *
     * @param menuItem the menu item
     */
    public void removeAllMenuItems(MenuItem menuItem) {
        menuItemChefRepository.deleteAll(menuItem.getChefs());
    }
}
