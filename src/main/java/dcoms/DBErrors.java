package dcoms;

public enum DBErrors {
    DUPLICATE_USERNAME("Username already taken."),
    DUPLICATE_EMAIL("Email already registered."),
    DUPLICATE_PHONE("Phone number already registered.");

    public String message;

    DBErrors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}