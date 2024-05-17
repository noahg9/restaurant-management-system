package be.kdg.programming5.programming5.controller.api;

import be.kdg.programming5.programming5.domain.AssignedChef;
import be.kdg.programming5.programming5.domain.Course;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.repository.AssignedChefRepository;
import be.kdg.programming5.programming5.repository.ChefRepository;
import be.kdg.programming5.programming5.repository.MenuItemRepository;
import be.kdg.programming5.programming5.service.MenuItemService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private MenuItemService menuItemService;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private ChefRepository chefRepository;
    @Autowired
    private AssignedChefRepository assignedChefRepository;
    @Autowired
    private MockMvc mockMvc;

    private long createdMenuItemId;
    private long createdAssignedChefId;

    /**
     * Sets each.
     */
    @BeforeEach
    public void setupEach() {
        var createdMenuItem = menuItemRepository.save(new MenuItem("Spaghetti", 10.0, Course.MAIN, false, 1));
        createdMenuItemId = createdMenuItem.getId();
        var createdAssignedChef = assignedChefRepository.save(
                new AssignedChef(
                        createdMenuItem,
                        chefRepository.findByUsername("noahg").orElseThrow(),
                        LocalDateTime.now()
                ));
        createdAssignedChefId = createdAssignedChef.getId();
    }

    /**
     * After each.
     */
    @AfterEach
    public void afterEach() {
        assignedChefRepository.deleteById(createdAssignedChefId);
        menuItemRepository.deleteById(createdMenuItemId);
    }

    /**
     * Gets chefs of menu item should return not found for non existing menu item.
     *
     * @throws Exception the exception
     */
    @Test
    public void getChefsOfMenuItemShouldReturnNotFoundForNonExistingMenuItem() throws Exception {
        mockMvc.perform(get("/api/menu-items/{menuItemId}/chefs", 999).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    /**
     * Gets chef of menu item should return no content when no chefs.
     *
     * @throws Exception the exception
     */
    @Test
    public void getChefOfMenuItemShouldReturnNoContentWhenNoChefs() throws Exception {
        mockMvc.perform(get("/api/menu-items/{menuItemId}/chefs", 5).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
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
                .andExpect(jsonPath("$[*].name", Matchers.contains("Lars Willemsens", "Raoul Van den Berge")))
        .andDo(print());
    }

    /**
     * Delete menu item should be forbidden if not signed in.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteMenuItemShouldBeForbiddenIfNotSignedIn() throws Exception {
        mockMvc.perform(
                        delete("/api/menu-items/{menuItemId}", createdMenuItemId)
                                .with(csrf()))
                .andExpect(status().isUnauthorized()); // 'isForbidden' if you forget CSRF (but CSRF should be there).
    }

    /**
     * Delete menu item should be allowed if chef is assigned.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("raoul")
    public void deleteMenuItemShouldBeAllowedIfChefIsAssigned() throws Exception {
        mockMvc.perform(
                        delete("/api/menu-items/{menuItemId}", createdMenuItemId)
                                .with(csrf()))
                .andExpect(status().isNoContent());
    }

    /**
     * Delete menu item should be allowed if admin.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("noahg")
    public void deleteMenuItemShouldBeAllowedIfAdmin() throws Exception {
        mockMvc.perform(
                        delete("/api/menu-items/{menuItemId}", createdMenuItemId)
                                .with(csrf()))
                .andExpect(status().isNoContent());
    }

    /**
     * Delete menu item should not be allowed if not assigned.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("noahg")
    public void deleteMenuItemShouldNotBeAllowedIfNotAssigned() throws Exception {
        // Let's unassign Raoul.
        assignedChefRepository.deleteById(createdAssignedChefId);

        mockMvc.perform(
                        delete("/api/menu-items/{menuItemId}", createdMenuItemId)
                                .with(csrf()))
                .andExpect(status().isForbidden());
    }
}
