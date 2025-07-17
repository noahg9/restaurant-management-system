package com.noahg9.restaurant.repository;

import com.noahg9.restaurant.domain.MenuAssignment;
import com.noahg9.restaurant.domain.Chef;
import com.noahg9.restaurant.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Menu assignment repository.
 */
public interface MenuAssignmentRepository extends JpaRepository<MenuAssignment, Long> {
    /**
     * Find by menu item id and chef id optional.
     *
     * @param menuItemId the menu item id
     * @param chefId     the chef id
     * @return the optional
     */
    Optional<MenuAssignment> findByMenuItemIdAndChefId(long menuItemId, long chefId);

    /**
     * Exists by chef and menu item boolean.
     *
     * @param chef     the chef
     * @param menuItem the menu item
     * @return the boolean
     */
    boolean existsByChefAndMenuItem(Chef chef, MenuItem menuItem);
}
