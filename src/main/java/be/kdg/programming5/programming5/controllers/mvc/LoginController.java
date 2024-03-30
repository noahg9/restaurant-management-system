package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(ChefController.class);

    @GetMapping("/login")
    public String showLogin(HttpSession session, Model model) {
        logger.info("Getting login page");
        String pageTitle = "Login";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        return "authentication/login";
    }
}
