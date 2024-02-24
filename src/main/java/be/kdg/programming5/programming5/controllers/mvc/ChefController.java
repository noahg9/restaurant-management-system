package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.domain.Restaurant;
import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import be.kdg.programming5.programming5.exceptions.DatabaseException;
import be.kdg.programming5.programming5.controllers.mvc.viewmodel.AddChefViewModel;
import be.kdg.programming5.programming5.service.ChefService;
import be.kdg.programming5.programming5.service.RestaurantService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling Chef-related operations.
 */
@Controller
@RequestMapping("/chef")
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
     * Handles GET request to retrieve the list of chefs.
     *
     * @param session Session object to manage session-related information.
     * @param model   Model object to add attributes for the view.
     * @return View name for displaying the list of chefs.
     */
    @GetMapping("/list")
    public String showChefList(HttpSession session, Model model) {
        String pageTitle = "Chef List";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("list", chefService.getChefs());
        return "chef/list";
    }

    /**
     * Handles GET request to display the form for adding a new chef.
     *
     * @param session Session object to manage session-related information.
     * @param model   Model object to add attributes for the view.
     * @return View name for the add chef form.
     */
    @GetMapping("/add")
    public String showAddChefForm(HttpSession session, Model model) {
        logger.debug("Showing chef form");
        String pageTitle = "Add Chef";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("addChefViewModel", new AddChefViewModel());
        model.addAttribute("restaurants", restaurantService.getRestaurants());
        return "chef/add";
    }

    /**
     * Handles POST request to add a new chef.
     *
     * @param viewModel Form data for adding a new chef.
     * @return Redirect URL after adding the chef.
     */
    @PostMapping("/add")
    public String addChef(@ModelAttribute("addChefViewModel") @Valid AddChefViewModel viewModel) {
        try {
            logger.debug("Adding chef {}", viewModel.getFirstName());
            Restaurant restaurant = restaurantService.getRestaurant(viewModel.getRestaurantId());
            chefService.addChef(
                    viewModel.getFirstName(),
                    viewModel.getLastName(),
                    viewModel.getDateOfBirth(),
                    restaurant
            );
        } catch (DatabaseException e) {
            logger.error("Database error adding chef: " + e.getMessage());
            return "dberror";
        } catch (Exception e) {
            logger.error("Error adding chef: " + e.getMessage());
            return "/error/error";
        }
        return "redirect:/chef/list";
    }


    /**
     * Handles POST request to delete a chef.
     *
     * @param chefId ID of the chef to be deleted.
     * @return Redirect URL after deleting the chef.
     */
    @PostMapping("/delete")
    public String deleteChef(@RequestParam("id") int chefId) {
        try {
            logger.debug("Deleting chef with ID: " + chefId);
            chefService.deleteChef(chefId);
            return "redirect:/chef/list";
        } catch (DatabaseException e) {
            logger.error("Database error deleting chef: " + e.getMessage());
            return "dberror";
        } catch (Exception e) {
            logger.error("Error deleting chef: " + e.getMessage());
            return "error/error";
        }
    }

    /**
     * Handles GET request to retrieve details of a specific chef.
     *
     * @param id    ID of the chef.
     * @param model Model object to add attributes for the view.
     * @return View name for displaying chef details.
     */
    @GetMapping("/details/{id}")
    public String showChefDetails(@PathVariable int id, HttpSession session, Model model) {
        String pageTitle = "Chef Details";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("chef", chefService.getChef(id));
        return "chef/details";
    }
}
