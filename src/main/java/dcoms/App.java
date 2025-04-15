package dcoms;

import dcoms.client.ClientInterface;
import dcoms.client.LoginUI;
import dcoms.utils.Env;
import dcoms.utils.SessionUtil;

public class App {
    public static void main(String[] args) {
        Env.loadEnv();
        new ClientInterface();

        UserSession session = SessionUtil.loadSession();

        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginUI loginFrame = new LoginUI(); // Always show LoginUI
            loginFrame.setLocationRelativeTo(null);

            if (session != null) {
                System.out.println("Auto-logged in as: " + session.username);
                loginFrame.autoLogin(session.username); // Trigger auto-login
            }

            loginFrame.setVisible(true);
        });
    }
}
