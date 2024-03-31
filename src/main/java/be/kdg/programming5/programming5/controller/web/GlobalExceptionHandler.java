package be.kdg.programming5.programming5.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Handle exception string.
     *
     * @param e     the e
     * @param model the model
     * @return the string
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        logger.error("An unexpected error occurred: {}", e.getMessage());
        model.addAttribute("errorMessage", "An unexpected error has occurred.");
        return "error/error";
    }
}
