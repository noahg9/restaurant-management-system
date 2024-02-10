package be.kdg.programming5.programming5.domain.util;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryUtil {
    public static List<Map<String, Object>> getHistory(HttpSession session) {
        // Retrieve session history
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> sessionHistory =
                (List<Map<String, Object>>) session.getAttribute("sessionHistory");

        // If session history doesn't exist, create a new one
        if (sessionHistory == null) {
            sessionHistory = new ArrayList<>();
            session.setAttribute("sessionHistory", sessionHistory);
        }

        return sessionHistory;
    }

    public static void updateHistory(HttpSession session, String page) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> sessionHistory =
                (List<Map<String, Object>>) session.getAttribute("sessionHistory");

        if (sessionHistory == null) {
            sessionHistory = new ArrayList<>();
        }

        Map<String, Object> entry = new HashMap<>();
        entry.put("page", page);
        entry.put("timestamp", LocalDateTime.now());
        sessionHistory.add(entry);

        session.setAttribute("sessionHistory", sessionHistory);
    }
}
