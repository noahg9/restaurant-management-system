package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.controllers.mvc.viewmodel.MenuItemViewModel;
import be.kdg.programming5.programming5.domain.Course;
import be.kdg.programming5.programming5.domain.MenuItemChef;
import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import be.kdg.programming5.programming5.service.MenuItemChefService;
import be.kdg.programming5.programming5.service.MenuItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import be.kdg.programming5.programming5.security.CustomUserDetails;

import static be.kdg.programming5.programming5.domain.ChefRole.ADMIN;

/**
 * The type Menu item controller.
 */
@Controller
public class MenuItemController {
    private final Logger logger = LoggerFactory.getLogger(MenuItemController.class);
    private final MenuItemService menuItemService;
    private final MenuItemChefService menuItemChefService;

    public MenuItemController(MenuItemService menuItemService, MenuItemChefService menuItemChefService) {
        this.menuItemService = menuItemService;
        this.menuItemChefService = menuItemChefService;
    }

    /**
     * All menu items model and view.
     *
     * @param session the session
     * @param model   the model
     * @return the model and view
     */
    @GetMapping("/menu-items")
    public ModelAndView allMenuItems(HttpSession session, Model model,
                                     @AuthenticationPrincipal CustomUserDetails user,
                                     HttpServletRequest request) {
        logger.info("Getting menu items");
        String pageTitle = "Menu Item";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);

        Long chefId = user != null ? user.getChefId() : null;
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
                                menuItem.getRestaurant().getName(),
                                request.isUserInRole(ADMIN.getCode()) ||
                                        chefId != null
                                                && menuItemChefService
                                                .isChefAssignedToMenuItem(menuItem.getId(), chefId)
                                )).toList());
        mav.addObject("courseValues", Course.values());
        return mav;
    }

    /**
     * One menu item model and view.
     *
     * @param menuItemId the menu item id
     * @param session    the session
     * @param model      the model
     * @return the model and view
     */
    @GetMapping("/menu-item")
    public ModelAndView oneMenuItem(@RequestParam("id") long menuItemId, HttpSession session, Model model,
                                    @AuthenticationPrincipal CustomUserDetails user,
                                    HttpServletRequest request) {
        logger.info("Getting menu item");
        String pageTitle = "Menu Item";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);

        Long chefId = user != null ? user.getChefId() : null;
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
                        request.isUserInRole(ADMIN.getCode()) ||
                                chefId != null &&
                                        menuItem.getChefs()
                                                .stream()
                                                .map(MenuItemChef::getChef)
                                                .anyMatch(chef -> chef.getId() == chefId)
                ));
        mav.addObject("courseValues", Course.values());
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
        String pageTitle = "Search Menu Items";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        return "menu/search-menu-items";
    }
}
