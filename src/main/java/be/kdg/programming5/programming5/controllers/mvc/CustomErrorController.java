package be.kdg.programming5.programming5.controllers.mvc;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Custom error controller to handle application errors.
 */
@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    /**
     * Instantiates a new Custom error controller.
     *
     * @param errorAttributes the error attributes
     */
    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    /**
     * Handles errors and displays a custom error page.
     *
     * @param model The model to add attributes for the error page.
     * @return The view name for the custom error page.
     */
    @RequestMapping("/error")
    public String handleError(Model model, WebRequest request) {
        String pageTitle = "Error";
        model.addAttribute("pageTitle", pageTitle);
        Map<String, Object> errorAttributesMap = errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.of());
        model.addAllAttributes(errorAttributesMap);
        return "error/error";
    }


}
