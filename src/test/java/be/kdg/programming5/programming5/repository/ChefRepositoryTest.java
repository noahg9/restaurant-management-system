package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.model.Chef;
import be.kdg.programming5.programming5.model.ChefRole;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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


    @BeforeAll
    public void setup() {
    }

    @AfterAll
    public void tearDown() {
    }

    @Test
    void findByIdWithMenuItemsShouldFetchRelatedData() {
        // Act
        var chefOptional = chefRepository.findByIdWithMenuItems(1);

        // Assert
        assertTrue(chefOptional.isPresent());
        var chef = chefOptional.get();
        assertEquals(1, chef.getId());
        assertEquals("Noah", chef.getFirstName());
        // There are other ways to compare lists in tests (Hamcrest, AssertJ, ...)
        var menuItems = chef.getMenuItems().stream().sorted((a1, a2) -> (int) (a1.getId() - a2.getId())).toList();
        assertEquals("Ceasar Salad", menuItems.get(0).getMenuItem().getName());
        assertEquals(LocalDateTime.of(2024, 3, 3, 12, 0, 0), menuItems.get(1).getAssignedDateTime());
        assertEquals("Grilled Salmon", menuItems.get(1).getMenuItem().getName());
        assertEquals(LocalDateTime.of(2024, 3, 7, 12, 0, 0), menuItems.get(2).getAssignedDateTime());
    }

    @Test
    void findByUsernameShouldReturnChef() {
        // Arrange
        var harry = chefRepository.save(new Chef("Harry", "Potter", LocalDate.of(2000, 1, 1), "harry", "password123", ChefRole.SOUS_CHEF));

        // Act
        Executable executable = () -> chefRepository.save(new Chef("Harry", "Smith", LocalDate.of(2000, 1, 1), "harry", "password123", ChefRole.SOUS_CHEF));

        // Assert
        assertThrows(DataIntegrityViolationException.class, executable);
        //assertThrows(DataIntegrityViolationException.class,
        // Act
        //() -> chefRepository.save(new Chef(
        //        "harry@kdg.be", "Harry2", "harry2", "password123",
        //        ChefRole.USER
        //)));
        chefRepository.delete(harry);
    }
}
