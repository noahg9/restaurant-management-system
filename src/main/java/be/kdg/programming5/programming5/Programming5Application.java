package be.kdg.programming5.programming5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Main class for starting the Restaurants Application.
 */
@SpringBootApplication
public class Programming5Application {

    /**
     * The entry point of the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        // Run the Spring Boot application and retrieve the application context
        ApplicationContext context = SpringApplication.run(Programming5Application.class, args);
    }

}
