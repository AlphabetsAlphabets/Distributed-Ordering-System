package dcoms;

public class Errors {
    public static String match(Exception e) {
        String message = e.getMessage();

        if (message == "[SQLITE_CONSTRAINT_UNIQUE] A UNIQUE constraint failed (UNIQUE constraint failed: users.email)") {
            message = DBErrors.DUPLICATE_EMAIL.getMessage();
        } else if (message == "[SQLITE_CONSTRAINT_UNIQUE] A UNIQUE constraint failed (UNIQUE constraint failed: users.phone)") {
            message = DBErrors.DUPLICATE_PHONE.getMessage();
        } else if (message == "[SQLITE_CONSTRAINT_UNIQUE] A UNIQUE constraint failed (UNIQUE constraint failed: users.username)") {
            message = DBErrors.DUPLICATE_USERNAME.getMessage();
        }

        return message;
    }
}