package be.kdg.programming5.programming5.controller.mvc;

import be.kdg.programming5.programming5.controller.mvc.viewmodel.ChefViewModel;
import be.kdg.programming5.programming5.controller.mvc.viewmodel.MenuItemViewModel;
import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.ChefRole;
import be.kdg.programming5.programming5.domain.CustomUserDetails;
import be.kdg.programming5.programming5.repository.ChefRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Chef controller test.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ChefControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ChefRepository chefRepository;

    /**
     * Chef view should be rendered with chef and menu item data.
     *
     * @throws Exception the exception
     */
    @Test
    public void chefViewShouldBeRenderedWithChefAndMenuItemData() throws Exception {
        var mvcResult = mockMvc.perform(
                get("/chef")
                        .queryParam("id", "1"))
                .andExpect(view().name("chef"))
                .andExpect(model().attribute("one_chef",
                        Matchers.samePropertyValuesAs(
                                new ChefViewModel(1, "John", "Doe", LocalDate.of(1999, 9, 9), "johnd", "Head Chef", false),
                                "menuItems")))
                .andReturn();

        var oneChef = (ChefViewModel) mvcResult.getModelAndView().getModel().get("one_chef");
        assertEquals(3, oneChef.getMenuItems().size());
        MatcherAssert.assertThat(oneChef.getMenuItems(), containsInAnyOrder(
                Matchers.samePropertyValuesAs(new MenuItemViewModel(1, "Spaghetti", 10.0, "Main", false, 1, false)),
                Matchers.samePropertyValuesAs(new MenuItemViewModel(2, "Lasagna", 12.0, "Main", false, 2, false)),
                Matchers.samePropertyValuesAs(new MenuItemViewModel(4, "Pizza", 8.0, "Main", false, 1, false))));
    }

    /**
     * Chef view should allow modification if chef signed in.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("larsw")
    public void chefViewShouldAllowModificationIfChefSignedIn() throws Exception {
        var mvcResult = mockMvc.perform(
                get("/chef")
                        .queryParam("id", "1"))
                .andExpect(view().name("chef"))
                .andExpect(model().attribute("one_chef",
                        Matchers.samePropertyValuesAs(new ChefViewModel(1, "Jane", "Doe", LocalDate.of(1989, 8, 8), "janed", "Head Chef", false),
                                "menuItems")))
                .andReturn();

        var chef = (ChefViewModel) mvcResult.getModelAndView().getModel().get("one_chef");
        var chefMenuItems = chef.getMenuItems();
        assertEquals(3, chefMenuItems.size());
    }

    /**
     * Chef view should allow modification if admin signed in.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("larsw")
    public void chefViewShouldAllowModificationIfAdminSignedIn() throws Exception {
        var mvcResult = mockMvc.perform(
                get("/chef")
                        .queryParam("id", "2"))
                .andExpect(view().name("chef"))
                .andExpect(model().attribute("one_chef",
                        Matchers.samePropertyValuesAs(new ChefViewModel(1, "Jane", "Doe", LocalDate.of(1989, 8, 8), "janed", "Head Chef", true),
                                "menuItems")))
                .andReturn();

        var chef = (ChefViewModel) mvcResult.getModelAndView().getModel().get("one_chef");
        var chefMenuItems = chef.getMenuItems();
        assertEquals(2, chefMenuItems.size());
    }

    /**
     * Chef view should not allow modification if different chef.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("gordonr")
    public void chefViewShouldNotAllowModificationIfDifferentChef() throws Exception {
        var mvcResult = mockMvc.perform(
                        get("/chef")
                                .queryParam("id", "1"))
                .andExpect(view().name("chef"))
                .andExpect(model().attribute("one_chef",
                        Matchers.samePropertyValuesAs(new ChefViewModel(1, "John", "Doe", LocalDate.of(1999, 9, 9), "johnd", "Head Chef", false),
                                "menuItems")))
                .andReturn();

        var chef = (ChefViewModel) mvcResult.getModelAndView().getModel().get("one_chef");
        var chefMenuItems = chef.getMenuItems();
        assertEquals(3, chefMenuItems.size());
    }

    @Test
    @WithUserDetails("larsw")
    public void updateChefShouldSucceedIfAdmin() throws Exception {
        var createdChef = chefRepository.save(
                new Chef("John", "Doe", LocalDate.of(1999, 9, 9), "johnd", "noah", ChefRole.HEAD_CHEF)
        );

        mockMvc.perform(post("/chef/update")
                        .param("id", String.valueOf(createdChef.getId()))
                        .param("firstName", "John")
                        .param("lastName", "Does")
                        .param("dateOfBirth", String.valueOf(LocalDate.of(1999, 9, 9)))
                        .param("username", "johnd")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/chef?id=" + createdChef.getId()));

        mockMvc.perform(get("/chef")
                        .queryParam("id", String.valueOf(createdChef.getId())))
                .andExpect(status().isOk())
                .andExpect(model().attribute("one_chef",
                        Matchers.samePropertyValuesAs(
                                new ChefViewModel(
                                        createdChef.getId(),
                                        "John",
                                        "Does",
                                        LocalDate.of(1999, 9, 9),
                                        "johnd",
                                        "Head Chef",
                                        false
                                ),
                                "menuItems"
                        )));

        chefRepository.deleteById(createdChef.getId());
    }

    @Test
    public void updateChefShouldSucceedIfSignedInAsSameChef() throws Exception {
        var createdChef = chefRepository.save(
                new Chef("John", "Doe", LocalDate.of(1999, 9, 9), "johnd", "noah", ChefRole.HEAD_CHEF)
        );
        var customUserDetails = new CustomUserDetails("johnd", "noah",
                List.of(), createdChef.getId());

        mockMvc.perform(post("/chef/update")
                        .param("id", String.valueOf(createdChef.getId()))
                        .param("firstName", "John")
                        .param("lastName", "Does")
                        .param("dateOfBirth", String.valueOf(LocalDate.of(1999, 9, 9)))
                        .param("username", "johnd")
                        .with(csrf())
                        .with(user(customUserDetails)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/chef?id=" + createdChef.getId()));

        mockMvc.perform(get("/chef")
                        .queryParam("id", String.valueOf(createdChef.getId())))
                .andExpect(status().isOk())
                .andExpect(model().attribute("one_chef",
                        Matchers.samePropertyValuesAs(
                                new ChefViewModel(
                                        createdChef.getId(),
                                        "John",
                                        "Does",
                                        LocalDate.of(1999, 9, 9),
                                        "johnd",
                                        "Head Chef",
                                        false
                                ),
                                "menuItems"
                        )));

        chefRepository.deleteById(createdChef.getId());
    }

    @Test
    @WithUserDetails("gordonr")
    public void updateChefShouldFailIfSignedInAsDifferentChef() throws Exception {
        var createdChef = chefRepository.save(
                new Chef("John", "Doe", LocalDate.of(1999, 9, 9), "johnd", "noah", ChefRole.HEAD_CHEF)
        );

        mockMvc.perform(post("/chef/update")
                        .param("id", String.valueOf(createdChef.getId()))
                        .param("firstName", "John")
                        .param("lastName", "Does")
                        .param("dateOfBirth", String.valueOf(LocalDate.of(1999, 9, 9)))
                        .param("username", "johnd")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/chef?id=" + createdChef.getId()));

        mockMvc.perform(get("/chef")
                        .queryParam("id", String.valueOf(createdChef.getId())))
                .andExpect(status().isOk())
                .andExpect(model().attribute("one_chef",
                        Matchers.samePropertyValuesAs(
                                new ChefViewModel(
                                        createdChef.getId(),
                                        "John",
                                        "Does",
                                        LocalDate.of(1999, 9, 9),
                                        "johnd",
                                        "Head Chef",
                                        false
                                ),
                                "menuItems"
                        )));

        chefRepository.deleteById(createdChef.getId());
    }

    @Test
    public void updateChefShouldFailIfNotSignedIn() throws Exception {
        var createdChef = chefRepository.save(
                new Chef("John", "Doe", LocalDate.of(1999, 9, 9), "johnd", "noah", ChefRole.HEAD_CHEF)
        );

        mockMvc.perform(post("/chef/update")
                        .param("id", String.valueOf(createdChef.getId()))
                        .param("firstName", "John")
                        .param("lastName", "Does")
                        .param("dateOfBirth", String.valueOf(LocalDate.of(1999, 9, 9)))
                        .param("username", "johnd")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/chef?id=" + createdChef.getId()));

        mockMvc.perform(get("/chef")
                        .queryParam("id", String.valueOf(createdChef.getId())))
                .andExpect(status().isOk())
                .andExpect(model().attribute("one_chef",
                        Matchers.samePropertyValuesAs(
                                new ChefViewModel(
                                        createdChef.getId(),
                                        "John",
                                        "Does",
                                        LocalDate.of(1999, 9, 9),
                                        "johnd",
                                        "Head Chef",
                                        false
                                ),
                                "menuItems"
                        )));

        chefRepository.deleteById(createdChef.getId());
    }

    @Test
    @WithUserDetails("larsw")
    public void updateChefShouldFailIfInvalidEmailAddress() throws Exception {
        var createdChef = chefRepository.save(
                new Chef("John", "Doe", LocalDate.of(1999, 9, 9), "johnd", "noah", ChefRole.HEAD_CHEF)
        );

        mockMvc.perform(post("/chef/update")
                        .param("id", String.valueOf(createdChef.getId()))
                        .param("firstName", "John")
                        .param("lastName", "Does")
                        .param("dateOfBirth", String.valueOf(LocalDate.of(1999, 9, 9)))
                        .param("username", "johnd")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/chef")
                        .queryParam("id", String.valueOf(createdChef.getId())))
                .andExpect(status().isOk())
                .andExpect(
                        model().attribute("one_chef",
                                Matchers.samePropertyValuesAs(
                                        new ChefViewModel(
                                                createdChef.getId(),
                                                "John",
                                                "Does",
                                                LocalDate.of(1999, 9, 9),
                                                "johnd",
                                                "Head Chef",
                                                false
                                        ),
                                        "menuItems"
                                )));

        chefRepository.deleteById(createdChef.getId());
    }
}
