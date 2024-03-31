package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.model.MenuItemChef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Menu item chef repository.
 */
public interface MenuItemChefRepository extends JpaRepository<MenuItemChef, Long> {
    /**
     * Find by menu item id and chef id optional.
     *
     * @param menuItemId the menu item id
     * @param chefId     the chef id
     * @return the optional
     */
    Optional<MenuItemChef> findByMenuItemIdAndChefId(long menuItemId, long chefId);
}
