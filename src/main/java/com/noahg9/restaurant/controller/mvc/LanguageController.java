package com.noahg9.restaurant.controller.mvc;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;
import java.util.Locale.Builder;

/**
 * The type Language controller.
 */
@Controller
public class LanguageController {
    private final Logger logger = LoggerFactory.getLogger(MenuItemController.class);
    private final LocaleResolver localeResolver;

    /**
     * Instantiates a new Language controller.
     *
     * @param localeResolver the locale resolver
     */
    public LanguageController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    /**
     * Switch language response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/switch-language")
    public ResponseEntity<Void> switchLanguage(HttpServletRequest request) {
        logger.info("Switching language");
        Locale currentLocale = localeResolver.resolveLocale(request);
        Locale newLocale = currentLocale.getLanguage().equals("en") ? new Builder().setLanguage("nl").build() : new Builder().setLanguage("en").build();
        localeResolver.setLocale(request, null, newLocale);
        return ResponseEntity.ok().build();
    }
}
