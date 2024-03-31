package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.model.*;
import be.kdg.programming5.programming5.repository.AssignedChefRepository;
import be.kdg.programming5.programming5.repository.ChefRepository;
import be.kdg.programming5.programming5.repository.MenuItemRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private AssignedChefRepository assignedChefRepository;

    private long testMenuItemId;
    private long testChefId;

    @BeforeAll
    public void setup() {
        Chef testChef = chefRepository.save(new Chef("John", "Doe", LocalDate.of(1998, 6, 20), "johnd", "john", ChefRole.SOUS_CHEF));
        MenuItem testMenuItem = menuItemRepository.save(new MenuItem("Cheese Bagel", 1.0, Course.MAIN, false, 1));
        testMenuItemId = testMenuItem.getId();
        testChefId = testChef.getId();
    }

    @AfterAll
    public void tearDown() {
        menuItemRepository.deleteById(testMenuItemId);
    }

    @Test
    void changeMenuItemShouldReturnTrueForExistingMenuItemAndUpdateSaidMenuItem() {
        // Arrange
        MenuItem createdMenuItem = menuItemRepository.save(new MenuItem("Cheese Bagel", 1.0, Course.MAIN, false, 1));

        // Act
        boolean result = menuItemService.changeMenuItem(createdMenuItem.getId(), "Ham Bagel", 1.5, true, 2);

        // Assert
        assertTrue(result);
        assertEquals("Ham Bagel", menuItemRepository.findById(createdMenuItem.getId()).get().getName());

        // Cleanup
        menuItemRepository.deleteById(createdMenuItem.getId());
    }

    @Test
    void changeMenuItemShouldReturnFalseForNonExistingMenuItem() {
        // Arrange: No need to arrange anything as we are testing with a non-existing menu item

        // Act
        boolean result = menuItemService.changeMenuItem(9999, "Ham Bagel", 1.5, true, 2);

        // Assert
        assertFalse(result); // The method should return false for a non-existing menu item
        assertTrue(menuItemRepository.findById(9999L).isEmpty()); // The menu item should not exist in the repository
    }

    @Test
    void getMenuItemsOfChefShouldReturnCorrectItems() {
        // Arrange
        Chef chef = chefRepository.save(new Chef("John", "Doe", LocalDate.of(1998, 6, 20), "johnd", "john", ChefRole.SOUS_CHEF));

        MenuItem menuItem1 = menuItemService.addMenuItem("Item 1", 10.0, Course.MAIN, false, 1);
        MenuItem menuItem2 = menuItemService.addMenuItem("Item 2", 12.0, Course.DESSERT, true, 2);

        // Create AssignedChef instances to associate Chef with MenuItem
        AssignedChef assignedChef1 = assignedChefRepository.save(new AssignedChef(menuItem1, chef, LocalDateTime.now()));
        AssignedChef assignedChef2 = assignedChefRepository.save(new AssignedChef(menuItem2, chef, LocalDateTime.now()));

        // Act
        List<MenuItem> chefMenuItems = menuItemService.getMenuItemsOfChef(chef.getId());

        // Assert
        assertEquals(2, chefMenuItems.size());
        assertTrue(chefMenuItems.contains(menuItem1));
        assertTrue(chefMenuItems.contains(menuItem2));
    }

}