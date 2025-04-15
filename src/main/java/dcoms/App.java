package dcoms;

import dcoms.client.ClientInterface;
import dcoms.client.LoginUI;
import dcoms.utils.Env;

public class App {
    public static void main(String[] args) {

        Env.loadEnv();
        new ClientInterface();


        // Ensure GUI runs on the Event Dispatch Thread (EDT) - good practice
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginUI loginFrame = new LoginUI();
            loginFrame.setLocationRelativeTo(null); // Center window
            loginFrame.setVisible(true);
        });
    }
}
