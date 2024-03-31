package be.kdg.programming5.programming5.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ChefRepositoryTest {
    @Autowired
    private ChefRepository chefRepository;

    @Test
    void findByIdWithMenuItemsShouldFetchRelatedData() {
        // Arrange

        // Act
        var chef = chefRepository.findByIdWithMenuItems(1).orElse(null);

        // Assert
        assertNotNull(chef);
        assertEquals(1, chef.getId());
        assertEquals(3, chef.getMenuItems().size());
        // Note: there are other ways to compare list objects (Hamcrest, AssertJ, ...)
        //       sorting is mandatory since the DB can return the records in any order
        var menuItems = chef.getMenuItems().stream().sorted((a1, a2) -> (int) (a1.getId() - a2.getId())).toList();
        assertEquals("Ceasar Salad", menuItems.get(0).getMenuItem().getName());
        assertEquals("Grilled Salmon", menuItems.get(1).getMenuItem().getName());
        assertEquals("Vanilla Ice Cream", menuItems.get(2).getMenuItem().getName());
    }

}