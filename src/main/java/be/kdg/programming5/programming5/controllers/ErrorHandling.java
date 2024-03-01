package be.kdg.programming5.programming5.controllers;

import be.kdg.programming5.programming5.controllers.api.dto.ErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorHandling {
    @ExceptionHandler(Exception.class)
    public Object notFound(Exception e, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api")) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDto(e.getMessage()));
        }
        final ModelAndView modelAndView = new ModelAndView(
                "error",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
