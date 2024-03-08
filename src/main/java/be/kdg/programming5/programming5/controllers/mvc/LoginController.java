package be.kdg.programming5.programming5.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Login controller.
 */
@Controller
public class LoginController {
    /**
     * Show login string.
     *
     * @return the string
     */
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
}
