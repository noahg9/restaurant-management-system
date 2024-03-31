package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ChefRepositoryTest {
    @Autowired
    private ChefRepository chefRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    void findByIdWithMenuItemsShouldFetchRelatedData() {
        // Arrange

        // Act
        Chef chef = chefRepository.findByIdWithMenuItems(1).orElse(null);

        // Assert
        assertNotNull(chef);
        assertEquals(1, chef.getId());
        assertEquals(3, chef.getMenuItems().size());
        // Note: there are other ways to compare list objects (Hamcrest, AssertJ, ...)
        //       sorting is mandatory since the DB can return the records in any order
        List<AssignedChef> menuItems = chef.getMenuItems().stream().sorted((a1, a2) -> (int) (a1.getId() - a2.getId())).toList();
        assertEquals("Ceasar Salad", menuItems.get(0).getMenuItem().getName());
        assertEquals("Grilled Salmon", menuItems.get(1).getMenuItem().getName());
        assertEquals("Vanilla Ice Cream", menuItems.get(2).getMenuItem().getName());
    }

    @Test
    void findByUsernameShouldReturnChef() {
        // Arrange
        String username = "gordonr";

        // Act
        Optional<Chef> chefOptional = chefRepository.findByUsername(username);

        // Assert
        assertTrue(chefOptional.isPresent(), "Chef with username '" + username + "' should exist");
        Chef chef = chefOptional.get();
        assertEquals(username, chef.getUsername(), "Username should match");
    }
}