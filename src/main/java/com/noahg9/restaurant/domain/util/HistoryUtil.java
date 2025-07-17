package com.noahg9.restaurant.domain.util;

import jakarta.servlet.http.HttpSession;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * The type History util.
 */
public class HistoryUtil {
    /**
     * Gets history.
     *
     * @param session the session
     * @return the history
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> getHistory(HttpSession session) {
        return (List<Map<String, Object>>) session.getAttribute("sessionHistory");
    }

    /**
     * Update history.
     *
     * @param session the session
     * @param page    the page
     */
    public static void updateHistory(HttpSession session, Object page) {
        List<Map<String, Object>> sessionHistory = getHistory(session);

        if (sessionHistory == null) {
            sessionHistory = new ArrayList<>();
        }

        Map<String, Object> entry = new HashMap<>();
        entry.put("page", page);
        entry.put("timestamp", ZonedDateTime.now(ZoneId.systemDefault()));
        sessionHistory.add(entry);

        session.setAttribute("sessionHistory", sessionHistory);
    }
}
