package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.exceptions.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String handleException(Exception e) {
        logger.error("An unexpected error occurred: {}", e.getMessage());
        return "error/error";
    }

    /**
     * Handles DatabaseException by logging the error and returning the database error view.
     *
     * @param e The caught DatabaseException.
     * @return The database error view name.
     */
    @ExceptionHandler(DatabaseException.class)
    public String handleDatabaseException(DatabaseException e) {
        logger.error("Database error occurred: {}", e.getMessage());
        return "error/dberror";
    }
}
