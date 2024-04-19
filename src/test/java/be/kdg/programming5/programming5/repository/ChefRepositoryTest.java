package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.model.*;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChefRepositoryTest {
    @Autowired
    private ChefRepository chefRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private AssignedChefRepository assignedChefRepository;

    private Chef chef;
    private MenuItem menuItem1;
    private MenuItem menuItem2;
    private AssignedChef assignedChef1;
    private AssignedChef assignedChef2;

    @BeforeAll
    public void setup() {
        chef = chefRepository.save(new Chef("John", "Doe", LocalDate.of(1998, 6, 20), "johnd", "john", ChefRole.SOUS_CHEF));
        menuItem1 = menuItemRepository.save(new MenuItem("Item 1", 10.0, Course.MAIN, false, 1));
        menuItem2 = menuItemRepository.save(new MenuItem("Item 2", 12.0, Course.DESSERT, true, 2));
        assignedChef1 = assignedChefRepository.save(new AssignedChef(menuItem1, chef, LocalDateTime.now()));
        assignedChef2 = assignedChefRepository.save(new AssignedChef(menuItem2, chef, LocalDateTime.now()));
    }

    @AfterAll
    public void tearDown() {
        assignedChefRepository.delete(assignedChef1);
        assignedChefRepository.delete(assignedChef2);
        chefRepository.delete(chef);
        menuItemRepository.delete(menuItem1);
        menuItemRepository.delete(menuItem2);
    }

    @Test
    void findByIdWithMenuItemsShouldFetchRelatedData() {
        // Assert
        assertNotNull(chef);
        assertEquals(2, chef.getMenuItems().size());
        List<AssignedChef> menuItems = chef.getMenuItems().stream().sorted((a1, a2) -> (int) (a1.getId() - a2.getId())).toList();
        assertEquals("Item 1", menuItems.get(0).getMenuItem().getName());
        assertEquals("Item 2", menuItems.get(1).getMenuItem().getName());
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
