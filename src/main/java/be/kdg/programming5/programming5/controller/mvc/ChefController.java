package be.kdg.programming5.programming5.controller.mvc;

import be.kdg.programming5.programming5.controller.mvc.viewmodel.ChefViewModel;
import be.kdg.programming5.programming5.controller.mvc.viewmodel.MenuItemViewModel;
import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.ChefRole;
import be.kdg.programming5.programming5.domain.CustomUserDetails;
import be.kdg.programming5.programming5.service.ChefService;
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

import java.util.Arrays;

import static be.kdg.programming5.programming5.domain.ChefRole.HEAD_CHEF;

/**
 * The type Chef controller.
 */
@Controller
public class ChefController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ChefController.class);
    private final ChefService chefService;

    /**
     * Instantiates a new Chef controller.
     *
     * @param chefService the chef service
     */
    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    /**
     * All chefs model and view.
     *
     * @param session the session
     * @param model   the model
     * @param mav     the mav
     * @return the model and view
     */
    @GetMapping("/chefs")
    public ModelAndView allChefs(HttpSession session, Model model, ModelAndView mav) {
        setupPage(session, model, "Chefs");
        mav.setViewName("chef/chefs");
        mav.addObject("all_chefs", chefService.getAllChefs().stream().map(chef -> new ChefViewModel(chef.getId(), chef.getFirstName(), chef.getLastName(), chef.getDateOfBirth(), chef.getUsername(), chef.getRole().getName(), false)).toList());
        mav.addObject("roleNames", Arrays.stream(ChefRole.values()).map(ChefRole::getName).toList());
        return mav;
    }

    /**
     * One chef model and view.
     *
     * @param chefId  the chef id
     * @param session the session
     * @param model   the model
     * @param mav     the mav
     * @param user    the user
     * @param request the request
     * @return the model and view
     */
    @GetMapping("/chef")
    public ModelAndView oneChef(@RequestParam("id") long chefId, HttpSession session, Model model, ModelAndView mav, @AuthenticationPrincipal CustomUserDetails user, HttpServletRequest request) {
        setupPage(session, model, "Chef");
        Chef chef = chefService.getChefWithMenuItems(chefId);
        mav.setViewName("chef/chef");
        mav.addObject("one_chef", new ChefViewModel(chef.getId(), chef.getFirstName(), chef.getLastName(), chef.getDateOfBirth(), chef.getUsername(), chef.getRole().getName(), user != null && (user.getChefId() == chefId || request.isUserInRole(HEAD_CHEF.getCode())), chef.getMenuItems().stream().map(assignedChef -> new MenuItemViewModel(assignedChef.getMenuItem().getId(), assignedChef.getMenuItem().getName(), assignedChef.getMenuItem().getPrice(), assignedChef.getMenuItem().getCourse().getName(), assignedChef.getMenuItem().isVegetarian(), assignedChef.getMenuItem().getSpiceLevel(), false)).toList()));
        mav.addObject("roleNames", Arrays.stream(ChefRole.values()).map(ChefRole::getName).toList());
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
        setupPage(session, model, "Search Chefs");
        return "chef/search-chefs";
    }

    /**
     * Register chef model and view.
     *
     * @param session the session
     * @param model   the model
     * @param mav     the mav
     * @return the model and view
     */
    @GetMapping("/register-chef")
    public ModelAndView registerChef(HttpSession session, Model model, ModelAndView mav) {
        setupPage(session, model, "Register Chef");
        mav.setViewName("chef/register-chef");
        mav.addObject("roleNames", Arrays.stream(ChefRole.values()).map(ChefRole::getName).toList());
        return mav;
    }
}
