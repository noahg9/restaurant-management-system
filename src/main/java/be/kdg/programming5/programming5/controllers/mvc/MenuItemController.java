package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.controllers.mvc.viewmodel.ChefViewModel;
import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import be.kdg.programming5.programming5.controllers.mvc.viewmodel.MenuItemViewModel;
import be.kdg.programming5.programming5.service.MenuItemService;
import jakarta.servlet.http.HttpSession;
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
public class MenuItemController {
    private final Logger logger = LoggerFactory.getLogger(MenuItemController.class);
    private final MenuItemService menuItemService;

    /**
     * Constructor to inject dependencies.
     *
     * @param menuItemService   The service for menu item operations.
     */
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
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
        String pageTitle = "Menu Item";
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
                                menuItem.getRestaurant().getId(),
                                menuItem.getRestaurant().getName()))
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
    public ModelAndView oneMenuItem(@RequestParam("id") long menuItemId, HttpSession session, Model model) {
        logger.info("Getting menu item");
        String pageTitle = "Menu Item";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);

        var menuItem = menuItemService.getMenuItemWithChefs(menuItemId);
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
                        menuItem.getRestaurant().getId(),
                        menuItem.getRestaurant().getName(),
                        menuItem.getChefs()
                                .stream().map(
                                        menuItemChef ->
                                                new ChefViewModel(
                                                        menuItemChef.getChef().getId(),
                                                        menuItemChef.getChef().getFirstName(),
                                                        menuItemChef.getChef().getLastName(),
                                                        menuItemChef.getChef().getDateOfBirth(),
                                                        menuItemChef.getChef().getRestaurant().getId(),
                                                        menuItemChef.getChef().getRestaurant().getName()
                                                )
                                ).toList()
                ));
        return mav;
    }

    @GetMapping("/search-menu-items")
    public String searchMenuItems(HttpSession session, Model model) {
        String pageTitle = "Search Menu Items";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        return "menu/search-menu-items";
    }
}
