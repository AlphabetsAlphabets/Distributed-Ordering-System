package dcoms.utils;

import dcoms.UserSession;

import java.io.*;

public class SessionUtil {
    private static final String SESSION_FILE = "user_session.ser";

    public static void saveSession(UserSession session) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SESSION_FILE))) {
            out.writeObject(session);
            File f = new File(SESSION_FILE);
            System.out.println("Session saved to: " + f.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save session: " + e.getMessage());
        }
    }
    
    public static UserSession loadSession() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SESSION_FILE))) {
            return (UserSession) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null; // No session or failed to load
        }
    }

    public static void clearSession() {
        File f = new File(SESSION_FILE);
        if (f.exists()) f.delete();
    }
}
