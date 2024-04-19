package be.kdg.programming5.programming5.controller.mvc;

import be.kdg.programming5.programming5.model.util.HistoryUtil;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

public abstract class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected void setupPage(HttpSession session, Model model, String pageTitle) {
        logger.info("Getting page: {}", pageTitle);
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
    }
}
