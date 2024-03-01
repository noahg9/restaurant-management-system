package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.domain.Course;
import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import be.kdg.programming5.programming5.exceptions.DatabaseException;
import be.kdg.programming5.programming5.controllers.mvc.viewmodel.MenuItemViewModel;
import be.kdg.programming5.programming5.service.MenuItemService;
import be.kdg.programming5.programming5.service.RestaurantService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
     * Displays the all_chefs of menu items.
     *
     * @param session The HttpSession object.
     * @param model   The model to add attributes.
     * @return The view name for the menu item all_chefs.
     */
    @GetMapping("/menu-items")
    public ModelAndView allMenuItems(HttpSession session, Model model) {
        logger.info("Getting menu items");
        String pageTitle = "Menu Item List";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);

        var mav = new ModelAndView();
        mav.setViewName("menu/menu-items");
        mav.addObject("all_menu_items",
                menuItemService.getAllMenuItems()
                        .stream()
                        .map(menuItem -> new MenuItemViewModel(
                                menuItem.getId(),
                                menuItem.getName(),
                                menuItem.getPrice(),
                                menuItem.getCourse(),
                                menuItem.isVegetarian(),
                                menuItem.getSpiceLvl(),
                                menuItem.getRestaurant().getId()))
                        .toList());
        return mav;
    }

    /**
     * Displays details of a specific menu item.
     *
     * @param menuItemId The ID of the menu item.
     * @param model The model to add attributes.
     * @return The view name for the menu item details.
     */
    @GetMapping("/menu-item")
    public ModelAndView oneMenuItem(@RequestParam("id") int menuItemId, HttpSession session, Model model) {
        logger.info("Getting menu item");
        String pageTitle = "Menu Item Details";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);

        var menuItem = menuItemService.getMenuItem(menuItemId);
        var mav = new ModelAndView();
        mav.setViewName("menu/menu-item");
        mav.addObject("one_menu_item",
                new MenuItemViewModel(
                        menuItem.getId(),
                        menuItem.getName(),
                        menuItem.getPrice(),
                        menuItem.getCourse(),
                        menuItem.isVegetarian(),
                        menuItem.getSpiceLvl(),
                        menuItem.getRestaurant().getId()
                ));
        return mav;
    }

    /**
     * Displays the form to add a new menu item.
     *
     * @param session The HttpSession object.
     * @param model   The model to add attributes.
     * @return The view name for the add menu item form.
     */
    @GetMapping("/add-menu-item")
    public String showAddMenuItemForm(HttpSession session, Model model) {
        logger.debug("Showing menu item form");
        String pageTitle = "Add Menu Item";
        HistoryUtil.updateHistory(session, "Add Menu Item");
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("menuItemViewModel", new MenuItemViewModel());
        model.addAttribute("courses", Course.values());
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        return "menu/add-menu-item";
    }

    /**
     * Handles the addition of a new menu item.
     *
     * @param viewModel The ViewModel containing data for the new menu item.
     * @return The redirect URL after adding the menu item.
     */
    @PostMapping("/add-menu-item")
    public String addMenuItem(@ModelAttribute("menuItemViewModel") @Valid MenuItemViewModel viewModel) {
        try {
            logger.debug("Adding menu item {}", viewModel.getName());
            menuItemService.addMenuItem(
                    viewModel.getName(),
                    viewModel.getPrice(),
                    viewModel.getCourse(),
                    viewModel.isVegetarian(),
                    viewModel.getSpiceLvl(),
                    restaurantService.getRestaurant(viewModel.getRestaurantId())
            );
        } catch (DatabaseException e) {
            logger.error("Database error adding menu item: " + e.getMessage());
            return "error/dberror";
        } catch (Exception e) {
            logger.error("Error adding menu item: " + e.getMessage());
            return "error/error";
        }
        return "redirect:/menu/menu-items";
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
            menuItemService.removeMenuItem(menuItemId);
            return "redirect:/menu/menu-items";
        } catch (DatabaseException e) {
            logger.error("Database error deleting menu item: " + e.getMessage());
            return "error/dberror";
        } catch (Exception e) {
            logger.error("Error deleting menu item: " + e.getMessage());
            return "error/error";
        }
    }

    @GetMapping("/search-menu-items")
    public String searchMenuItems(HttpSession session, Model model) {
        String pageTitle = "Search Menu Items";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        return "menu/search-menu-items";
    }
}
