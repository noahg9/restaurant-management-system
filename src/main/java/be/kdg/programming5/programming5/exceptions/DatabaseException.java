package be.kdg.programming5.programming5.exceptions;

/**
 * Custom exception class to represent database-related errors.
 * Extends RuntimeException for unchecked exception handling.
 */
public class DatabaseException extends RuntimeException {

    /**
     * Constructs a new DatabaseException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DatabaseException(String message) {
        super(message);
    }
}
