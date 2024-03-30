package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.domain.ChefRole;
import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(ChefController.class);

    @GetMapping("/login")
    public String showLogin(HttpSession session, Model model) {
        logger.info("Getting login page");
        String pageTitle = "Login";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        return "authentication/login";
    }

    @GetMapping("/registration")
    public ModelAndView showRegistration(HttpSession session, Model model, ModelAndView mav) {
        logger.info("Getting registration page");
        String pageTitle = "Registration";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);

        mav.setViewName("authentication/registration");
        mav.addObject("roleValues", ChefRole.values());
        return mav;
    }
}
