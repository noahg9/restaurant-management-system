package be.kdg.programming5.programming5.controller.web;

import be.kdg.programming5.programming5.model.util.HistoryUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Home controller.
 */
@Controller
public class HomeController {

    /**
     * Gets home.
     *
     * @param session the session
     * @param model   the model
     * @return the home
     */
    @GetMapping({"", "/", "/home"})
    public String getHome(HttpSession session, Model model) {
        String pageTitle = "Home";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        return "index";
    }
}
