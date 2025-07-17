package com.noahg9.restaurant.service;

import com.noahg9.restaurant.domain.Course;
import com.noahg9.restaurant.domain.MenuItem;
import com.noahg9.restaurant.repository.MenuAssignmentRepository;
import com.noahg9.restaurant.repository.ChefRepository;
import com.noahg9.restaurant.repository.MenuItemRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Menu item service test.
 */
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MenuItemServiceTest {
    @Autowired
    private MenuItemService menuItemService;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private ChefRepository chefRepository;
    @Autowired
    private MenuAssignmentRepository menuAssignmentRepository;

    private long testMenuItemId;

    /**
     * Sets .
     */
    @BeforeAll
    public void setup() {
        var testMenuItem = menuItemRepository.save(new MenuItem("Lasagne", 10, Course.MAIN, false, 1));
        testMenuItemId = testMenuItem.getId();
    }

    /**
     * Tear down.
     */
    @AfterAll
    public void tearDown() {
        menuItemRepository.deleteById(testMenuItemId);
    }

    /**
     * Change menu item should return true for existing menu item and update said menu item.
     */
    @Test
    void changeMenuItemShouldReturnTrueForExistingMenuItemAndUpdateSaidMenuItem() {
        // Arrange
        var createdMenuItem = menuItemRepository.save(new MenuItem("Lasagne", 10, Course.MAIN, false, 1));

        // Act
        var result = menuItemService.updateMenuItem(
                createdMenuItem.getId(), "Lasagne", 1.0, true, 1);

        // Assert
        assertTrue(result);
        assertTrue(menuItemRepository.findById(createdMenuItem.getId()).isPresent());
        assertEquals("Lasagne",
                menuItemRepository.findById(createdMenuItem.getId()).get().getName());

        // (cleanup)
        menuItemRepository.deleteById(createdMenuItem.getId());
    }

    /**
     * Change menu item should return false for non existing menu item.
     */
    @Test
    void changeMenuItemShouldReturnFalseForNonExistingMenuItem() {
        // Act
        var result = menuItemService.updateMenuItem(
                9999, "Lasagne", 1.0, true, 1);

        // Assert
        assertFalse(result);
        assertTrue(menuItemRepository.findById(9999L).isEmpty());
    }
}
