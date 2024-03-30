package be.kdg.programming5.programming5.domain;

/**
 * The enum Chef role.
 */
public enum ChefRole {
    /**
     * Chef chef role.
     */
    Chef("ROLE_DEVELOPER"),
    /**
     * Admin chef role.
     */
    Admin("ROLE_ADMIN");

    private final String code;

    ChefRole(String code) {
        this.code = code;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }
}
