package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling home-related operations.
 */
@Controller
public class HomeController {

    /**
     * Retrieves and displays the home page.
     *
     * @param session The HttpSession object.
     * @param model   the model
     * @return The view name for the home page.
     */
    @GetMapping({"", "/", "/home"})
    public String getHome(HttpSession session, Model model) {
        String pageTitle = "Home";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        return "index";
    }
}
