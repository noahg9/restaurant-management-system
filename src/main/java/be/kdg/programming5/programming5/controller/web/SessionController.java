package be.kdg.programming5.programming5.controller.web;

import be.kdg.programming5.programming5.model.util.HistoryUtil;
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
public class SessionController extends BaseController {
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
        setupPage(session, model, "Session History");
        List<Map<String, Object>> fullHistory = HistoryUtil.getHistory(session);
        int size = fullHistory.size();
        List<Map<String, Object>> recentHistory = fullHistory.subList(Math.max(0, size - 16), size);
        model.addAttribute("history", recentHistory);
        return "session/history";
    }
}