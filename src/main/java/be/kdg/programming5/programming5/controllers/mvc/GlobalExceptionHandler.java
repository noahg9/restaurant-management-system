package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.exceptions.DatabaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.webjars.NotFoundException;

/**
 * GlobalExceptionHandler handles exceptions thrown in controllers.
 * It provides specific handling for general exceptions and database-related exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Handles general exceptions by logging the error and returning the error view.
     *
     * @param e The caught exception.
     * @return The error view name.
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        logger.error("An unexpected error occurred: {}", e.getMessage());
        model.addAttribute("errorMessage", "An unexpected error has occurred.");
        return "error/error";
    }
}
