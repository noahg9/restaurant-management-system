package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.Course;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.repository.MenuItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * The type Menu item service unit test.
 */
@SpringBootTest
@ActiveProfiles("test")
class MenuItemServiceUnitTest {
    @Autowired
    private MenuItemService menuItemService;

    @MockBean
    private MenuItemRepository menuItemRepository;

    /**
     * Update menu item fails when menu item doesnt exist.
     */
    @Test
    void updateMenuItemFailsWhenMenuItemDoesntExist() {
        // Arrange
        given(menuItemRepository.findById(7777L)).willReturn(Optional.empty());

        // Act
        var updateSucceeded = menuItemService.updateMenuItem(7777L, "Lasagne", 1.0, true, 1);

        // Assert
        assertFalse(updateSucceeded);
        verify(menuItemRepository, never()).save(any());
    }

    /**
     * Update menu item succeeds when menu item exists.
     */
    @Test
    void updateMenuItemSucceedsWhenMenuItemExists() {
        // Arrange
        var menuItem = new MenuItem("Brocolli", 1.0, Course.MAIN, true, 1);
        menuItem.setId(7777L);
        given(menuItemRepository.findById(7777L)).willReturn(Optional.of(menuItem));

        // Act
        var updateSucceeded = menuItemService.updateMenuItem(7777L, "Lasagne", 1.0, true, 1);

        // Assert
        assertTrue(updateSucceeded);
        ArgumentCaptor<MenuItem> menuItemCaptor = ArgumentCaptor.forClass(MenuItem.class);
        verify(menuItemRepository/*, times(1)*/).save(menuItemCaptor.capture());
        assertEquals("Lasagne", menuItemCaptor.getValue().getName());
    }
}
