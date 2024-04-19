package be.kdg.programming5.programming5.controller.mvc;

import be.kdg.programming5.programming5.controller.mvc.viewmodel.ChefViewModel;
import be.kdg.programming5.programming5.controller.mvc.viewmodel.MenuItemViewModel;
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

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * The type Chef controller test.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ChefControllerTest {
    @Autowired
    private MockMvc mockMvc;

    /**
     * Chef view should be rendered with chef and menu item data.
     *
     * @throws Exception the exception
     */
    @Test
    public void chefViewShouldBeRenderedWithChefAndMenuItemData() throws Exception {
        var mvcResult = mockMvc.perform(get("/chef").queryParam("id", "1")).andExpect(view().name("chef")).andExpect(model().attribute("one_chef", Matchers.samePropertyValuesAs(new ChefViewModel(1, "John", "Doe", LocalDate.of(1999, 9, 9), "johnd", "Head Chef", false), "menuItems"))).andReturn();

        var oneChef = (ChefViewModel) mvcResult.getModelAndView().getModel().get("one_chef");
        assertEquals(3, oneChef.getMenuItems().size());
        MatcherAssert.assertThat(oneChef.getMenuItems(), containsInAnyOrder(Matchers.samePropertyValuesAs(new MenuItemViewModel(1, "Spaghetti", 10.0, "Main", false, 1, false)), Matchers.samePropertyValuesAs(new MenuItemViewModel(2, "Lasagna", 12.0, "Main", false, 2, false)), Matchers.samePropertyValuesAs(new MenuItemViewModel(4, "Pizza", 8.0, "Main", false, 1, false))));
    }

    /**
     * Chef view should allow modification if chef signed in.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("lars")
    public void chefViewShouldAllowModificationIfChefSignedIn() throws Exception {
        var mvcResult = mockMvc.perform(get("/chef").queryParam("id", "1")).andExpect(view().name("chef")).andExpect(model().attribute("one_chef", Matchers.samePropertyValuesAs(new ChefViewModel(1, "Jane", "Doe", LocalDate.of(1989, 8, 8), "janed", "Head Chef", false), "menuItems"))).andReturn();

        var oneChef = (ChefViewModel) mvcResult.getModelAndView().getModel().get("one_chef");
        assertEquals(3, oneChef.getMenuItems().size());
    }

    /**
     * Chef view should not allow modification if different chef.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("raoul")
    public void chefViewShouldNotAllowModificationIfDifferentChef() throws Exception {
        var mvcResult = mockMvc.perform(get("/chef").queryParam("id", "1")).andExpect(view().name("chef")).andExpect(model().attribute("one_chef", Matchers.samePropertyValuesAs(new ChefViewModel(1, "John", "Doe", LocalDate.of(1999, 9, 9), "johnd", "Head Chef", false), "menuItems"))).andReturn();

        var oneChef = (ChefViewModel) mvcResult.getModelAndView().getModel().get("one_chef");
        assertEquals(3, oneChef.getMenuItems().size());
    }

    /**
     * Chef view should allow modification if admin signed in.
     *
     * @throws Exception the exception
     */
    @Test
    @WithUserDetails("lars")
    public void chefViewShouldAllowModificationIfAdminSignedIn() throws Exception {
        var mvcResult = mockMvc.perform(get("/chef").queryParam("id", "2")).andExpect(view().name("chef")).andExpect(model().attribute("one_chef", Matchers.samePropertyValuesAs(new ChefViewModel(1, "Jane", "Doe", LocalDate.of(1989, 8, 8), "janed", "Head Chef", false), "menuItems"))).andReturn();

        var oneChef = (ChefViewModel) mvcResult.getModelAndView().getModel().get("one_chef");
        assertEquals(2, oneChef.getMenuItems().size());
    }
}
