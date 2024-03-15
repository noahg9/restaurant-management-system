package be.kdg.programming5.programming5.domain;

public enum ChefRole {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private final String code;

    ChefRole(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
