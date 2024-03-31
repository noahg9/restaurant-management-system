package be.kdg.programming5.programming5.dto;

/**
 * The type Error dto.
 */
public class ErrorDto {
    private String message;

    /**
     * Instantiates a new Error dto.
     */
    public ErrorDto() {
    }

    /**
     * Instantiates a new Error dto.
     *
     * @param message the message
     */
    public ErrorDto(String message) {
        this.message = message;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
