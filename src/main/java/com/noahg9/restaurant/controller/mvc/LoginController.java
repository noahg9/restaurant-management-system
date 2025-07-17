package com.noahg9.restaurant.controller.mvc;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Login controller.
 */
@Controller
public class LoginController extends BaseController {
    /**
     * Show login string.
     *
     * @param session the session
     * @param model   the model
     * @return the string
     */
    @GetMapping("/login")
    public String showLogin(HttpSession session, Model model) {
        setupPage(session, model, "Login");
        return "authentication/login";
    }
}
