package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import be.kdg.programming5.programming5.exceptions.DatabaseException;
import be.kdg.programming5.programming5.controllers.mvc.viewmodel.ChefViewModel;
import be.kdg.programming5.programming5.service.ChefService;
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
 * Controller class for handling Chef-related operations.
 */
@Controller
public class ChefController {
    private static final Logger logger = LoggerFactory.getLogger(ChefController.class);
    private final ChefService chefService;
    private final RestaurantService restaurantService;

    /**
     * Constructor to inject dependencies.
     *
     * @param chefService       Service for Chef-related operations.
     * @param restaurantService Service for Restaurant-related operations.
     */
    public ChefController(ChefService chefService, RestaurantService restaurantService) {
        this.chefService = chefService;
        this.restaurantService = restaurantService;
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
        mav.addObject("all_chefs",
                chefService.getAllChefs()
                        .stream()
                        .map(chef -> new ChefViewModel(
                                chef.getId(),
                                chef.getFirstName(),
                                chef.getLastName(),
                                chef.getDateOfBirth(),
                                chef.getRestaurant().getId(),
                                chef.getRestaurant().getName()))
                        .toList());
        return mav;
    }

    /**
     * Handles GET request to retrieve details of a specific chef.
     *
     * @param chefId ID of the chef.
     * @param model Model object to add attributes for the view.
     * @return View name for displaying chef details.
     */
    @GetMapping("/chef")
    public ModelAndView oneChef(@RequestParam("id") int chefId, HttpSession session, Model model) {
        logger.info("Getting chef");
        String pageTitle = "Chef";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);

        var chef = chefService.getChef(chefId);
        var mav = new ModelAndView();
        mav.setViewName("chef/chef");
        mav.addObject("one_chef",
                new ChefViewModel(
                        chef.getId(),
                        chef.getFirstName(),
                        chef.getLastName(),
                        chef.getDateOfBirth(),
                        chef.getRestaurant().getId(),
                        chef.getRestaurant().getName()
                ));
        return mav;
    }

    /**
     * Handles POST request to delete a chef.
     *
     * @param chefId ID of the chef to be deleted.
     * @return Redirect URL after deleting the chef.
     */
    @PostMapping("/delete-chef")
    public String deleteChef(@RequestParam("id") int chefId) {
        try {
            logger.debug("Deleting chef with ID: " + chefId);
            chefService.removeChef(chefId);
            return "redirect:/chefs";
        } catch (DatabaseException e) {
            logger.error("Database error deleting chef: " + e.getMessage());
            return "error/dberror";
        } catch (Exception e) {
            logger.error("Error deleting chef: " + e.getMessage());
            return "error/error";
        }
    }

    @GetMapping("/search-chefs")
    public String searchChefs(HttpSession session, Model model) {
        String pageTitle = "Search Chefs";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        return "chef/search-chefs";
    }
}
