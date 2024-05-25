package be.kdg.programming5.programming5.controller.api;

import be.kdg.programming5.programming5.domain.ChefRole;
import be.kdg.programming5.programming5.domain.CustomUserDetails;
import be.kdg.programming5.programming5.service.MenuAssignmentService;
import be.kdg.programming5.programming5.service.MenuItemService;
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

    @Test
    public void deleteMenuItemShouldBeUnauthorizedIfNotSignedIn() throws Exception {
        mockMvc.perform(delete("/api/menu-items/{id}", 7777L)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());

        verify(menuItemService, never()).deleteMenuItem(7777L);
    }

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
