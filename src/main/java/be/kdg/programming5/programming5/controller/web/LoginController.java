package be.kdg.programming5.programming5.controller.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends BaseController {
    @GetMapping("/login")
    public String showLogin(HttpSession session, Model model) {
        setupPage(session, model, "Login");
        return "authentication/login";
    }
}
