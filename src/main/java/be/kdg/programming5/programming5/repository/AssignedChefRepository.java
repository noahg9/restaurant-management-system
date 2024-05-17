package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.domain.AssignedChef;
import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Menu item chef repository.
 */
public interface AssignedChefRepository extends JpaRepository<AssignedChef, Long> {
    /**
     * Find by menu item id and chef id optional.
     *
     * @param menuItemId the menu item id
     * @param chefId     the chef id
     * @return the optional
     */
    Optional<AssignedChef> findByMenuItemIdAndChefId(long menuItemId, long chefId);

    boolean existsByChefAndMenuItem(Chef chef, MenuItem menuItem);
}
