package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.controllers.mvc.viewmodel.ChefViewModel;
import be.kdg.programming5.programming5.controllers.mvc.viewmodel.MenuItemViewModel;
import be.kdg.programming5.programming5.domain.ChefRole;
import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import be.kdg.programming5.programming5.service.ChefService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import be.kdg.programming5.programming5.security.CustomUserDetails;

import static be.kdg.programming5.programming5.domain.ChefRole.Admin;

/**
 * The type Chef controller.
 */
@Controller
public class ChefController {
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
     * @return the model and view
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
                                chef.getRole(),
                                chef.getRestaurant().getId(),
                                chef.getRestaurant().getName(),
                                false))
                        .toList());
        mav.addObject("roleValues", ChefRole.values());
        return mav;
    }

    /**
     * One chef model and view.
     *
     * @param chefId  the chef id
     * @param session the session
     * @param model   the model
     * @return the model and view
     */
    @GetMapping("/chef")
    public ModelAndView oneChef(@RequestParam("id") long chefId, HttpSession session, Model model,
                                @AuthenticationPrincipal CustomUserDetails user,
                                HttpServletRequest request) {
        logger.info("Getting chef");
        String pageTitle = "Chef";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);

        var chef = chefService.getChefWithMenuItems(chefId);
        var mav = new ModelAndView();
        mav.setViewName("chef/chef");
        mav.addObject("one_chef",
                new ChefViewModel(
                        chef.getId(),
                        chef.getFirstName(),
                        chef.getLastName(),
                        chef.getDateOfBirth(),
                        chef.getRole(),
                        chef.getRestaurant().getId(),
                        chef.getRestaurant().getName(),
                        user != null && (user.getChefId() == chefId || request.isUserInRole(Admin.getCode())),
                        chef.getMenuItems()
                                .stream()
                                .map(menuItemChef -> new MenuItemViewModel(
                                        menuItemChef.getMenuItem().getId(),
                                        menuItemChef.getMenuItem().getName(),
                                        menuItemChef.getMenuItem().getPrice(),
                                        menuItemChef.getMenuItem().getCourse(),
                                        menuItemChef.getMenuItem().isVegetarian(),
                                        menuItemChef.getMenuItem().getSpiceLvl(),
                                        menuItemChef.getMenuItem().getRestaurant().getId(),
                                        menuItemChef.getMenuItem().getRestaurant().getName(),
                                        false
                                        )
                                )
                                .toList()
                )
        );
        mav.addObject("roleValues", ChefRole.values());
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

    @PostMapping("/chef/update")
    public String updateChef(@Valid ChefViewModel chefViewModel,
                                  BindingResult bindingResult,
                                  @AuthenticationPrincipal CustomUserDetails user,
                                  HttpServletRequest request) {
        if ((user.getChefId() == chefViewModel.getId() || request.isUserInRole(Admin.getCode()))
                && (!bindingResult.hasErrors())) {
            chefService.changeChef(
                    chefViewModel.getId(),
                    chefViewModel.getFirstName(),
                    chefViewModel.getLastName(),
                    chefViewModel.getDateOfBirth());
        }
        return "redirect:/chef?id=" + chefViewModel.getId();
    }
}
