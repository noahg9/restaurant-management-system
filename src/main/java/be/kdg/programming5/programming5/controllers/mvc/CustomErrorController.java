package be.kdg.programming5.programming5.controllers.mvc;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Custom error controller to handle application errors.
 */
@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    /**
     * Handles errors and displays a custom error page.
     *
     * @param model The model to add attributes for the error page.
     * @return The view name for the custom error page.
     */
    @RequestMapping("")
    public String handleError(Model model) {
        String pageTitle = "Error";
        model.addAttribute("pageTitle", pageTitle);
        return "error/error";
    }
}
