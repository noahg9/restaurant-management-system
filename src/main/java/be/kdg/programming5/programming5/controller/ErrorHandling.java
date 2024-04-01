package be.kdg.programming5.programming5.controller;

import be.kdg.programming5.programming5.dto.ErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Error handling.
 */
@ControllerAdvice
public class ErrorHandling {
    /**
     * Not found object.
     *
     * @param e       the e
     * @param request the request
     * @return the object
     */
    @ExceptionHandler(Exception.class)
    public Object notFound(Exception e, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api")) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDto(e.getMessage()));
        }
        final ModelAndView modelAndView = new ModelAndView(
                "error/error",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
