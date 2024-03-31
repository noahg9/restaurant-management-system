package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.model.Course;
import be.kdg.programming5.programming5.model.MenuItem;
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

    private long testMenuItemId;

    @BeforeAll
    public void setup() {
        MenuItem testMenuItem = menuItemRepository.save(new MenuItem("Cheese Bagel", 1.0, Course.MAIN, false, 1));
        testMenuItemId = testMenuItem.getId();
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
        // Arrange

        // Act
        boolean result = menuItemService.changeMenuItem(9999, "Ham Bagel", 1.5, true, 2);

        // Assert
        assertFalse(result);
        assertTrue(menuItemRepository.findById(9999L).isEmpty());
    }
}