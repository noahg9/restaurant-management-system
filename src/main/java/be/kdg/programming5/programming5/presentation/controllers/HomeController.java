package be.kdg.programming5.programming5.presentation.controllers;

import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * Controller class for handling home-related operations.
 */
@Controller
public class HomeController {

    @Autowired
    private LocaleResolver localeResolver;

    /**
     * Retrieves and displays the home page.
     *
     * @param session The HttpSession object.
     * @return The view name for the home page.
     */
    @GetMapping({"", "/", "/home"})
    public String getHome(HttpSession session, Model model) {
        String pageTitle = "Home";
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
        return "home";
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
