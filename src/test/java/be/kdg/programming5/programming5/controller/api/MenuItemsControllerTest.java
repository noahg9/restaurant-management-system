package be.kdg.programming5.programming5.controller.api;

import be.kdg.programming5.programming5.domain.AssignedChef;
import be.kdg.programming5.programming5.domain.Course;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.dto.NewMenuItemDto;
import be.kdg.programming5.programming5.repository.AssignedChefRepository;
import be.kdg.programming5.programming5.repository.ChefRepository;
import be.kdg.programming5.programming5.repository.MenuItemRepository;
import be.kdg.programming5.programming5.service.MenuItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    @Autowired
    private ObjectMapper objectMapper;

    private long createdMenuItemId;
    private long createdAssignedChefId;

    /**
     * Sets each.
     */
    @BeforeEach
    public void setupEach() {
        var createdMenuItem = menuItemRepository.save(
                new MenuItem("Spaghetti", 10.0, Course.MAIN, false, 1));
        createdMenuItemId = createdMenuItem.getId();

        var createdAssignedChef = assignedChefRepository.save(
                new AssignedChef(createdMenuItem, chefRepository.findByUsername("gordonr").orElseThrow(), LocalDateTime.now()));
        createdAssignedChefId = createdAssignedChef.getId();
    }

    /**
     * After each.
     */
    @AfterEach
    public void tearDownEach() {
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
        mockMvc.perform(
                get("/api/menu-items/{menuItemId}/chefs", 999)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Gets chef of menu item should return no content when no chefs.
     *
     * @throws Exception the exception
     */
    @Test
    public void getChefOfMenuItemShouldReturnNoContentIfNoAssignedChefs() throws Exception {
        mockMvc.perform(
                get("/api/menu-items/{menuItemId}/chefs", 5)
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
                .andExpect(jsonPath("$[*].name",
                        Matchers.containsInAnyOrder("Lars Willemsens", "Noah Guerin")))
                .andDo(print());
    }

    /**
     * Delete menu item should be forbidden if not signed in.
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
     * Delete menu item should be allowed if chef is assigned.
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
     * Delete menu item should be allowed if admin.
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
     * Delete menu item should not be allowed if not assigned.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("noahg")
    public void deleteMenuItemIsNotAllowedIfNotAssigned() throws Exception {
        mockMvc.perform(
                delete("/api/menu-items/{menuItemId}", 1)
                        .with(csrf()))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/api/menu-items/{menuItemId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("larsw")
    public void deleteMenuItemShouldReturnNotFoundIfMenuItemDoesNotExist() throws Exception {
        mockMvc.perform(
                        delete("/api/menu-items/{menuItemId}", 999999)
                                .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails("gordonr")
    public void addMenuItemShouldBeBadRequestIfMissingMessageBody() throws Exception {
        mockMvc.perform(post("/api/menu-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails("gordonr")
    public void addMenuItemShouldBeCreatedIfCorrectMessageBody() throws Exception {
        var mvcResult = mockMvc.perform(post("/api/menuItems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(new NewMenuItemDto(
                                "Lasagne", 9, "Main", false, 1
                        ))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Lasagne"))
                .andExpect(jsonPath("$.price").isNumber())
                .andExpect(jsonPath("$.course").value("Main"))
                .andExpect(jsonPath("$.vegetarian").isBoolean())
                .andExpect(jsonPath("$.spiceLevel").isNumber())
                .andReturn();

        var httpResponseBody = mvcResult.getResponse().getContentAsString();
        long menuItemId = (Integer) JsonPath.read(httpResponseBody, "$.id");

        mockMvc.perform(get("/api/menu-items/{id}", menuItemId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(menuItemId));

        mockMvc.perform(delete("/api/menu-items/{id}", menuItemId)
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }
}
