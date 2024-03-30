package be.kdg.programming5.programming5.controllers.mvc;

import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * The type Session controller.
 */
@Controller
public class SessionController {
    private final Logger logger = LoggerFactory.getLogger(SessionController.class);

    /**
     * Gets history.
     *
     * @param session the session
     * @param model   the model
     * @return the history
     */
    @GetMapping("/history")
    public String getHistory(HttpSession session, Model model) {
        logger.info("Getting history");
        String pageTitle = "History";
        HistoryUtil.updateHistory(session, pageTitle);
        List<Map<String, Object>> history = HistoryUtil.getHistory(session);
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("history", history);
        return "session/history";
    }
}