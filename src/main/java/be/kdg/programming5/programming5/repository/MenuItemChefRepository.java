package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.domain.MenuItemChef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Menu item chef repository.
 */
public interface MenuItemChefRepository extends JpaRepository<MenuItemChef, Long> {
    Optional<MenuItemChef> findByMenuItemIdAndChefId(long menuItemId, long chefId);
}
