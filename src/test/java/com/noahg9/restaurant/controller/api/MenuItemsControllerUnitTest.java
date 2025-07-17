package com.noahg9.restaurant.controller.api;

import com.noahg9.restaurant.domain.ChefRole;
import com.noahg9.restaurant.domain.CustomUserDetails;
import com.noahg9.restaurant.service.MenuAssignmentService;
import com.noahg9.restaurant.service.MenuItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Menu items controller unit test.
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class MenuItemsControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuItemService menuItemService;

    @MockBean
    private MenuAssignmentService menuAssignmentService;

    /**
     * Delete menu item should be unauthorized if not signed in.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteMenuItemShouldBeUnauthorizedIfNotSignedIn() throws Exception {
        mockMvc.perform(delete("/api/menu-items/{id}", 7777L)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());

        verify(menuItemService, never()).deleteMenuItem(7777L);
    }

    /**
     * Delete menu item should be allowed if chef is assigned.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteMenuItemShouldBeAllowedIfChefIsAssigned() throws Exception {
        long menuItemId = 7777L;
        long chefId = 8888L;
        var userDetails = new CustomUserDetails("gordonr", "noah", List.of(), chefId);
        given(menuAssignmentService.isChefAssignedToMenuItem(menuItemId, chefId))
                .willReturn(true);
        given(menuItemService.deleteMenuItem(menuItemId)).willReturn(true);

        mockMvc.perform(delete("/api/menu-items/{id}", menuItemId)
                        .with(user(userDetails))
                        .with(csrf()))
                .andExpect(status().isNoContent());

        verify(menuItemService).deleteMenuItem(menuItemId);
    }

    /**
     * Delete menu item should be allowed if admin.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteMenuItemShouldBeAllowedIfAdmin() throws Exception {
        long menuItemId = 7777L;
        long chefId = 8888L;
        var userDetails = new CustomUserDetails("jamieo", "noah",
                List.of(new SimpleGrantedAuthority(ChefRole.HEAD_CHEF.getCode())),
                chefId);
        given(menuAssignmentService.isChefAssignedToMenuItem(menuItemId, chefId))
                .willReturn(false);
        given(menuItemService.deleteMenuItem(menuItemId)).willReturn(true);

        mockMvc.perform(delete("/api/menu-items/{id}", menuItemId)
                        .with(user(userDetails))
                        .with(csrf()))
                .andExpect(status().isNoContent());

        verify(menuItemService).deleteMenuItem(menuItemId);
    }

    /**
     * Delete menu item should not be allowed if not assigned.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteMenuItemShouldNotBeAllowedIfNotAssigned() throws Exception {
        long menuItemId = 7777L;
        long chefId = 8888L;
        var userDetails = new CustomUserDetails("jamieo", "noah",
                List.of(), chefId);
        given(menuAssignmentService.isChefAssignedToMenuItem(menuItemId, chefId))
                .willReturn(false);

        mockMvc.perform(delete("/api/menu-items/{id}", menuItemId)
                        .with(user(userDetails))
                        .with(csrf()))
                .andExpect(status().isForbidden());

        verify(menuItemService, never()).deleteMenuItem(menuItemId);
    }

    /**
     * Delete menu item should be not found if menu item doesnt exist.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteMenuItemShouldBeNotFoundIfMenuItemDoesntExist() throws Exception {
        long menuItemId = 7777L;
        long chefId = 8888L;
        var userDetails = new CustomUserDetails("jamieo", "noah",
                List.of(new SimpleGrantedAuthority(ChefRole.HEAD_CHEF.getCode())),
                chefId);
        given(menuAssignmentService.isChefAssignedToMenuItem(menuItemId, chefId))
                .willReturn(false);
        given(menuItemService.deleteMenuItem(menuItemId)).willReturn(false);

        mockMvc.perform(delete("/api/menu-items/{id}", menuItemId)
                        .with(user(userDetails))
                        .with(csrf()))
                .andExpect(status().isNotFound());

        verify(menuItemService).deleteMenuItem(menuItemId);
    }
}
