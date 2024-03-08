package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.controllers.mvc.viewmodel.ChefViewModel;
import be.kdg.programming5.programming5.controllers.mvc.viewmodel.MenuItemViewModel;
import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import be.kdg.programming5.programming5.service.ChefService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class for handling Chef-related operations.
 */
@Controller
public class ChefController {
    private static final Logger logger = LoggerFactory.getLogger(ChefController.class);
    private final ChefService chefService;

    /**
     * Constructor to inject dependencies.
     *
     * @param chefService Service for Chef-related operations.
     */
    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    /**
     * Handles GET request to retrieve the all_chefs of chefs.
     *
     * @param session Session object to manage session-related information.
     * @param model   Model object to add attributes for the view.
     * @return View name for displaying the all_chefs of chefs.
     */
    @GetMapping("/chefs")
    public ModelAndView allChefs(HttpSession session, Model model) {
        logger.info("Getting chefs");
        String pageTitle = "Chefs";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);

        var mav = new ModelAndView();
        mav.setViewName("chef/chefs");
        mav.addObject("all_chefs", chefService.getAllChefs().stream().map(chef -> new ChefViewModel(chef.getId(), chef.getFirstName(), chef.getLastName(), chef.getDateOfBirth(), chef.getRestaurant().getId(), chef.getRestaurant().getName())).toList());
        return mav;
    }

    /**
     * Handles GET request to retrieve details of a specific chef.
     *
     * @param chefId  ID of the chef.
     * @param session the session
     * @param model   Model object to add attributes for the view.
     * @return View name for displaying chef details.
     */
    @GetMapping("/chef")
    public ModelAndView oneChef(@RequestParam("id") long chefId, HttpSession session, Model model) {
        logger.info("Getting chef");
        String pageTitle = "Chef";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);

        var chef = chefService.getChefWithMenuItems(chefId);
        var mav = new ModelAndView();
        mav.setViewName("chef/chef");
        mav.addObject("one_chef", new ChefViewModel(chef.getId(), chef.getFirstName(), chef.getLastName(), chef.getDateOfBirth(), chef.getRestaurant().getId(), chef.getRestaurant().getName(), chef.getMenuItems().stream().map(menuItemChef -> new MenuItemViewModel(menuItemChef.getMenuItem().getId(), menuItemChef.getMenuItem().getName(), menuItemChef.getMenuItem().getPrice(), menuItemChef.getMenuItem().getCourse(), menuItemChef.getMenuItem().isVegetarian(), menuItemChef.getMenuItem().getSpiceLvl(), menuItemChef.getMenuItem().getRestaurant().getId(), menuItemChef.getMenuItem().getRestaurant().getName())).toList()));
        return mav;
    }

    /**
     * Search chefs string.
     *
     * @param session the session
     * @param model   the model
     * @return the string
     */
    @GetMapping("/search-chefs")
    public String searchChefs(HttpSession session, Model model) {
        String pageTitle = "Search Chefs";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        return "chef/search-chefs";
    }
}
