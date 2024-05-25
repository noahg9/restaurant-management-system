package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.*;
import be.kdg.programming5.programming5.repository.MenuAssignmentRepository;
import be.kdg.programming5.programming5.repository.ChefRepository;
import be.kdg.programming5.programming5.repository.MenuItemRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

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

    @BeforeAll
    public void setup() {
        var testMenuItem = menuItemRepository.save(new MenuItem("Lasagne", 10, Course.MAIN, false, 1));
        testMenuItemId = testMenuItem.getId();
    }

    @AfterAll
    public void tearDown() {
        menuItemRepository.deleteById(testMenuItemId);
    }

    @Test
    void changeMenuItemShouldReturnTrueForExistingMenuItemAndUpdateSaidMenuItem() {
        // Arrange
        var createdMenuItem = menuItemRepository.save(new MenuItem("Lasagne", 10, Course.MAIN, false, 1));

        // Act
        var result = menuItemService.updateMenuItem(
                createdMenuItem.getId(), "Lasagne", 1.0, true, 1);

        // Assert
        assertTrue(result);
        assertEquals("Lasagne",
                menuItemRepository.findById(createdMenuItem.getId()).get().getName());

        // (cleanup)
        menuItemRepository.deleteById(createdMenuItem.getId());
    }

    @Test
    void changeMenuItemShouldReturnFalseForNonExistingMenuItem() {
        // Act
        var result = menuItemService.updateMenuItem(
                9999, "Lasagne", 1.0, true, 1);

        // Assert
        assertFalse(result);
        // This is a bit over the top (my "assumptions" were clear).
        assertTrue(menuItemRepository.findById(9999L).isEmpty());
    }

    @Test
    void getMenuItemsOfChefShouldReturnCorrectItems() {
        // Act
        //List<MenuItem> chefMenuItems = menuItemService.getMenuItemsOfChef(chef.getId());

        // Assert
        //assertEquals(2, chefMenuItems.size());
        //assertTrue(chefMenuItems.contains(menuItem1));
        //assertTrue(chefMenuItems.contains(menuItem2));
    }

}
