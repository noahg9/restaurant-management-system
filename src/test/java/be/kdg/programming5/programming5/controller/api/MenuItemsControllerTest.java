package be.kdg.programming5.programming5.controller.api;

import be.kdg.programming5.programming5.domain.MenuAssignment;
import be.kdg.programming5.programming5.domain.Course;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.repository.MenuAssignmentRepository;
import be.kdg.programming5.programming5.repository.ChefRepository;
import be.kdg.programming5.programming5.repository.MenuItemRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Menu items controller test.
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class MenuItemsControllerTest {
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private ChefRepository chefRepository;
    @Autowired
    private MenuAssignmentRepository menuAssignmentRepository;
    @Autowired
    private MockMvc mockMvc;

    private long createdMenuItemId;
    private long createdMenuAssignmentId;

    /**
     * Sets each.
     */
    @BeforeEach
    public void setupEach() {
        var createdMenuItem = menuItemRepository.save(
                new MenuItem("Spaghetti", 10.0, Course.MAIN, false, 1));
        createdMenuItemId = createdMenuItem.getId();

        var createdMenuAssignmentId1 = menuAssignmentRepository.save(
                new MenuAssignment(createdMenuItem, chefRepository.findByUsername("gordonr").orElseThrow(), LocalDateTime.now()));
        createdMenuAssignmentId = createdMenuAssignmentId1.getId();
    }

    /**
     * Tear down each.
     */
    @AfterEach
    public void tearDownEach() {
        menuAssignmentRepository.deleteById(createdMenuAssignmentId);
        menuItemRepository.deleteById(createdMenuItemId);
    }

    /**
     * Gets chefs of menu item should return not found for non existing menu item.
     *
     * @throws Exception the exception
     */
    @Test
    public void getChefsOfMenuItemShouldReturnNotFoundForNonExistingMenuItem() throws Exception {
        mockMvc.perform(
                get("/api/menu-items/{menuItemId}/chefs", 999)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Gets chef of menu item should return no content if no assigned chefs.
     *
     * @throws Exception the exception
     */
    @Test
    public void getChefOfMenuItemShouldReturnNoContentIfNoAssignedChefs() throws Exception {
        mockMvc.perform(
                get("/api/menu-items/{menuItemId}/chefs", 3)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    /**
     * Gets chefs of menu item should return ok with chefs.
     *
     * @throws Exception the exception
     */
    @Test
    public void getChefsOfMenuItemShouldReturnOkWithChefs() throws Exception {
        mockMvc.perform(
                get("/api/menu-items/{menuItemId}/chefs", 2)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].username",
                        Matchers.containsInAnyOrder("noahg", "davidc")))
                .andDo(print());
    }

    /**
     * Delete menu item is not allowed if not signed in.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteMenuItemIsNotAllowedIfNotSignedIn() throws Exception {
        mockMvc.perform(
                delete("/api/menu-items/{menuItemId}", createdMenuItemId)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    /**
     * Delete menu item is allowed if chef is assigned.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("gordonr")
    public void deleteMenuItemIsAllowedIfChefIsAssigned() throws Exception {
        mockMvc.perform(
                delete("/api/menu-items/{menuItemId}", createdMenuItemId)
                        .with(csrf()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/menu-items/{menuItemId}", createdMenuItemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Delete menu item is allowed if admin.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("noahg")
    public void deleteMenuItemIsAllowedIfAdmin() throws Exception {
        mockMvc.perform(
                delete("/api/menu-items/{menuItemId}", createdMenuItemId)
                        .with(csrf()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/menu-items/{menuItemId}", createdMenuItemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Delete menu item is not allowed if not assigned.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("gordonr")
    public void deleteMenuItemIsNotAllowedIfNotAssigned() throws Exception {
        mockMvc.perform(
                delete("/api/menu-items/{menuItemId}", 1)
                        .with(csrf()))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/api/menu-items/{menuItemId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Delete menu item should return not found if menu item does not exist.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("larsw")
    public void deleteMenuItemShouldReturnNotFoundIfMenuItemDoesNotExist() throws Exception {
        mockMvc.perform(
                        delete("/api/menu-items/{menuItemId}", 999999)
                                .with(csrf()))
                .andExpect(status().isNotFound());
    }
}
