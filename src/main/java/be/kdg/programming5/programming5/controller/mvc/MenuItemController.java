package be.kdg.programming5.programming5.controller.mvc;

import be.kdg.programming5.programming5.controller.mvc.viewmodel.ChefViewModel;
import be.kdg.programming5.programming5.controller.mvc.viewmodel.MenuItemViewModel;
import be.kdg.programming5.programming5.controller.mvc.viewmodel.RecipeViewModel;
import be.kdg.programming5.programming5.domain.*;
import be.kdg.programming5.programming5.service.AssignedChefService;
import be.kdg.programming5.programming5.service.MenuItemService;
import be.kdg.programming5.programming5.service.RecipeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static be.kdg.programming5.programming5.domain.ChefRole.HEAD_CHEF;

/**
 * The type Menu item controller.
 */
@Controller
public class MenuItemController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(MenuItemController.class);
    private final MenuItemService menuItemService;
    private final RecipeService recipeService;
    private final AssignedChefService assignedChefService;

    /**
     * Instantiates a new Menu item controller.
     *
     * @param menuItemService     the menu item service
     * @param recipeService       the recipe service
     * @param assignedChefService the assigned chef service
     */
    public MenuItemController(MenuItemService menuItemService, RecipeService recipeService, AssignedChefService assignedChefService) {
        this.menuItemService = menuItemService;
        this.recipeService = recipeService;
        this.assignedChefService = assignedChefService;
    }

    /**
     * All menu items model and view.
     *
     * @param session the session
     * @param model   the model
     * @param user    the user
     * @param request the request
     * @return the model and view
     */
    @GetMapping("/menu-items")
    public ModelAndView allMenuItems(HttpSession session, Model model, ModelAndView mav, @AuthenticationPrincipal CustomUserDetails user, HttpServletRequest request) {
        setupPage(session, model, "Menu Items");
        Long chefId = user != null ? user.getChefId() : null;
        mav.setViewName("menu/menu-items");
        mav.addObject("all_menu_items",
                menuItemService.getAllMenuItems()
                        .stream()
                        .map(menuItem -> new MenuItemViewModel(
                                menuItem.getId(),
                                menuItem.getName(),
                                menuItem.getPrice(),
                                menuItem.getCourse().getName(),
                                menuItem.isVegetarian(),
                                menuItem.getSpiceLevel(),
                                request.isUserInRole(HEAD_CHEF.getCode())
                                        || chefId != null
                                        && assignedChefService.isChefAssignedToMenuItem(menuItem.getId(), chefId)))
                        .toList());
        mav.addObject("courseNames", Arrays.stream(Course.values()).map(Course::getName).toList());
        return mav;
    }

    /**
     * One menu item model and view.
     *
     * @param menuItemId the menu item id
     * @param session    the session
     * @param model      the model
     * @param user       the user
     * @param request    the request
     * @return the model and view
     */
    @GetMapping("/menu-item")
    public ModelAndView oneMenuItem(@RequestParam("id") long menuItemId, HttpSession session, Model model, ModelAndView mav, @AuthenticationPrincipal CustomUserDetails user, HttpServletRequest request) {
        setupPage(session, model, "Menu Item");
        Long chefId = user != null ? user.getChefId() : null;
        MenuItem menuItem = menuItemService.getMenuItemWithChefs(menuItemId);
        Recipe recipe = menuItem.getRecipe();
        mav.setViewName("menu/menu-item");
        mav.addObject("one_menu_item",
                new MenuItemViewModel(
                        menuItem.getId(),
                        menuItem.getName(),
                        menuItem.getPrice(),
                        menuItem.getCourse().getName(),
                        menuItem.isVegetarian(),
                        menuItem.getSpiceLevel(),
                        request.isUserInRole(HEAD_CHEF.getCode()) || chefId != null && menuItem.getChefs().stream().map(AssignedChef::getChef).anyMatch(chef -> Objects.equals(chef.getId(), chefId)),
                        menuItem.getChefs().stream().map(assignedChef -> new ChefViewModel(
                                assignedChef.getChef().getId(),
                                assignedChef.getChef().getFirstName(),
                                assignedChef.getChef().getLastName(),
                                assignedChef.getChef().getDateOfBirth(),
                                assignedChef.getChef().getUsername(),
                                assignedChef.getChef().getRole().getName(),
                                false)).toList()));
        mav.addObject("one_recipe", new RecipeViewModel(recipe.getId(), recipe.getInstructions(), recipe.getCookingTime(), recipe.getDifficulty()));
        mav.addObject("courseNames", Arrays.stream(Course.values()).map(Course::getName).toList());
        return mav;
    }

    /**
     * Search menu items string.
     *
     * @param session the session
     * @param model   the model
     * @return the string
     */
    @GetMapping("/search-menu-items")
    public String searchMenuItems(HttpSession session, Model model) {
        setupPage(session, model, "Search Menu Items");
        return "menu/search-menu-items";
    }

    @GetMapping("/menu-items-csv")
    @PreAuthorize("hasRole('ROLE_HEAD_CHEF')")
    public ModelAndView uploadCsv(HttpSession session, Model model) {
        setupPage(session, model, "Menu Items CSV");
        var mav = new ModelAndView("menu/menu-items-csv");
        mav.getModel().put("inProgress", false);
        return mav;
    }

    @PostMapping("/menu-items-csv")
    @PreAuthorize("hasRole('ROLE_HEAD_CHEF')")
    public ModelAndView uploadCsv(@RequestParam("menu-items-csv") MultipartFile file) throws IOException {
        menuItemService.processMenuItemsCsv(file.getInputStream());
        var mav = new ModelAndView("menu/menu-items-csv");
        mav.getModel().put("inProgress", true);
        return mav;
    }
}
