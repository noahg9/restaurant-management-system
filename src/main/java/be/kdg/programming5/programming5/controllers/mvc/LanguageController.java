package be.kdg.programming5.programming5.controllers.mvc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
public class LanguageController {

    private final LocaleResolver localeResolver;

    public LanguageController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }


    /**
     * Handles the language switch functionality.
     *
     * @param request The HttpServletRequest object.
     * @return ResponseEntity indicating the success of the language switch.
     */
    @PostMapping("/switch-language")
    public ResponseEntity<Void> switchLanguage(HttpServletRequest request) {
        Locale currentLocale = localeResolver.resolveLocale(request);
        Locale newLocale = currentLocale.getLanguage().equals("en") ? new Locale("nl") : new Locale("en");
        localeResolver.setLocale(request, null, newLocale);
        return ResponseEntity.ok().build();
    }
}
