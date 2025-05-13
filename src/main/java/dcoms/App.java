package dcoms;

import java.rmi.RemoteException;

import dcoms.client.ClientInterface;
import dcoms.client.LoginUI;
import dcoms.utils.Env;
import dcoms.utils.Session;
import dcoms.utils.UserSession;

public class App {
    public static void main(String[] args) {
        Env.loadEnv();

        try {
            new ClientInterface();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        UserSession session = Session.loadSession();

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
