package be.kdg.programming5.programming5.controller.mvc;

import be.kdg.programming5.programming5.domain.util.HistoryUtil;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

/**
 * The type Base controller.
 */
public abstract class BaseController {
    /**
     * The Logger.
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Sets page.
     *
     * @param session   the session
     * @param model     the model
     * @param pageTitle the page title
     */
    protected void setupPage(HttpSession session, Model model, String pageTitle) {
        logger.info("Getting page: {}", pageTitle);
        HistoryUtil.updateHistory(session, pageTitle);
        model.addAttribute("pageTitle", pageTitle);
    }
}
