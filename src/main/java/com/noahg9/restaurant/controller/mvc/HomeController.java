package com.noahg9.restaurant.controller.mvc;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Home controller.
 */
@Controller
public class HomeController extends BaseController {

    /**
     * Gets home.
     *
     * @param session the session
     * @param model   the model
     * @return the home
     */
    @GetMapping({"", "/", "/home"})
    public String getHome(HttpSession session, Model model) {
        setupPage(session, model, "Home");
        return "index";
    }
}
