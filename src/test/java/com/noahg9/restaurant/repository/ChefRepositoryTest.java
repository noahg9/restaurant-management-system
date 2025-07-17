package com.noahg9.restaurant.repository;

import com.noahg9.restaurant.domain.Chef;
import com.noahg9.restaurant.domain.ChefRole;
import com.noahg9.restaurant.domain.MenuAssignment;
import com.noahg9.restaurant.domain.MenuItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Chef repository test.
 */
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChefRepositoryTest {
    @Autowired
    private ChefRepository chefRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private MenuAssignmentRepository menuAssignmentRepository;

    /**
     * Find by id with menu items should fetch related data.
     */
    @Test
    void findByIdWithMenuItemsShouldFetchRelatedData() {
        // Arrange
        var chef = chefRepository.findByIdWithMenuItems(1).get();
        var menuItems = chef.getMenuItems().stream().sorted((a1, a2) -> (int) (a1.getId() - a2.getId())).toList();

        // Assert
        assertEquals(1, chef.getId());
        assertEquals("Noah", chef.getFirstName());
        assertEquals("Ceasar Salad", menuItems.get(0).getMenuItem().getName());
        assertEquals(LocalDateTime.of(2024, 3, 5, 12, 0, 0), menuItems.get(1).getAssignedDateTime());
        assertEquals("Grilled Salmon", menuItems.get(1).getMenuItem().getName());
        assertEquals(LocalDateTime.of(2024, 3, 7, 12, 0, 0), menuItems.get(2).getAssignedDateTime());
    }

    /**
     * Delete chef should not delete associated menu items.
     */
    @Test
    void deleteChefShouldNotDeleteAssociatedMenuItems() {
        // Arrange
        var chef = chefRepository.findByIdWithMenuItems(2).get();
        var menuItems = chef.getMenuItems();
        var menuItemIds = menuItems.stream().map(MenuAssignment::getMenuItem).map(MenuItem::getId).toList();

        // Act
        for (var menuItem : menuItems) {
            menuAssignmentRepository.delete(menuAssignmentRepository.findByMenuItemIdAndChefId(menuItem.getMenuItem().getId(), chef.getId()).get());
        }
        chefRepository.delete(chefRepository.findByIdWithMenuItems(2).get());

        // Assert
        for (var menuItemId : menuItemIds) {
            assertTrue(menuItemRepository.findById(menuItemId).isPresent());
        }
    }

    /**
     * Chef username should be unique.
     */
    @Test
    void chefUsernameShouldBeUnique() {
        // Arrange
        var harry = chefRepository.save(new Chef(
                "Harry", "Potter", LocalDate.of(2000, 1, 1), "harry", "password123", ChefRole.SOUS_CHEF));

        // Act
        Executable executable = () -> chefRepository.save(new Chef(
                "Harry", "Smith", LocalDate.of(2000, 1, 1), "harry", "password123", ChefRole.SOUS_CHEF));

        // Assert
        assertThrows(DataIntegrityViolationException.class, executable);
        chefRepository.delete(harry);
    }
}
