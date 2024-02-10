package be.kdg.programming5.programming5.presentation.controllers;

import be.kdg.programming5.programming5.domain.Course;
import be.kdg.programming5.programming5.domain.Restaurant;
import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import be.kdg.programming5.programming5.exceptions.DatabaseException;
import be.kdg.programming5.programming5.presentation.viewmodel.AddMenuItemViewModel;
import be.kdg.programming5.programming5.service.MenuItemService;
import be.kdg.programming5.programming5.service.RestaurantService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling menu item-related operations.
 */
@Controller
@RequestMapping("/menu")
public class MenuItemController {
    private final Logger logger = LoggerFactory.getLogger(MenuItemController.class);
    private final MenuItemService menuItemService;
    private final RestaurantService restaurantService;

    /**
     * Constructor to inject dependencies.
     *
     * @param menuItemService   The service for menu item operations.
     * @param restaurantService The service for restaurant operations.
     */
    public MenuItemController(MenuItemService menuItemService, RestaurantService restaurantService) {
        this.menuItemService = menuItemService;
        this.restaurantService = restaurantService;
    }

    /**
     * Displays the list of menu items.
     *
     * @param session The HttpSession object.
     * @param model   The model to add attributes.
     * @return The view name for the menu item list.
     */
    @GetMapping("/list")
    public String showMenuItemList(HttpSession session, Model model) {
        logger.info("Getting menu items");
        String pageTitle = "Menu Item List";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("list", menuItemService.getMenuItems());
        return "menu/list";
    }

    /**
     * Displays the form to add a new menu item.
     *
     * @param session The HttpSession object.
     * @param model   The model to add attributes.
     * @return The view name for the add menu item form.
     */
    @GetMapping("/add")
    public String showAddMenuItemForm(HttpSession session, Model model) {
        logger.debug("Showing menu item form");
        String pageTitle = "Add Menu Item";
        HistoryUtil.updateHistory(session, "Add Menu Item");
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("addMenuItemViewModel", new AddMenuItemViewModel());
        model.addAttribute("courses", Course.values());
        model.addAttribute("restaurants", restaurantService.getRestaurants());
        return "menu/add";
    }

    /**
     * Handles the addition of a new menu item.
     *
     * @param viewModel The ViewModel containing data for the new menu item.
     * @return The redirect URL after adding the menu item.
     */
    @PostMapping("/add")
    public String addMenuItem(@ModelAttribute("addMenuItemViewModel") @Valid AddMenuItemViewModel viewModel) {
        try {
            logger.debug("Adding menu item {}", viewModel.getName());
            Restaurant restaurant = restaurantService.getRestaurant(viewModel.getRestaurantId());
            menuItemService.addMenuItem(
                    viewModel.getName(),
                    viewModel.getPrice(),
                    viewModel.getCourse(),
                    viewModel.getVegetarian(),
                    viewModel.getSpiceLvl(),
                    restaurant
            );
        } catch (DatabaseException e) {
            logger.error("Database error adding menu item: " + e.getMessage());
            return "dberror";
        } catch (Exception e) {
            logger.error("Error adding menu item: " + e.getMessage());
            return "/error/error";
        }
        return "redirect:/menu/list";
    }

    /**
     * Handles the deletion of a menu item.
     *
     * @param menuItemId The ID of the menu item to be deleted.
     * @return The redirect URL after deleting the menu item.
     */
    @PostMapping("/delete")
    public String deleteMenuItem(@RequestParam("id") int menuItemId) {
        try {
            logger.debug("Deleting menu item with ID: " + menuItemId);
            menuItemService.deleteMenuItem(menuItemId);
            return "redirect:/menu/list";
        } catch (DatabaseException e) {
            logger.error("Database error deleting menu item: " + e.getMessage());
            return "dberror";
        } catch (Exception e) {
            logger.error("Error deleting menu item: " + e.getMessage());
            return "error/error";
        }
    }

    /**
     * Displays details of a specific menu item.
     *
     * @param id    The ID of the menu item.
     * @param model The model to add attributes.
     * @return The view name for the menu item details.
     */
    @GetMapping("/details/{id}")
    public String showMenuItemDetails(@PathVariable int id, Model model) {
        String pageTitle = "Menu Item Details";
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("menu_item", menuItemService.getMenuItem(id));
        return "menu/details";
    }
}
