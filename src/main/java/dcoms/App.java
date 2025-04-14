package dcoms;

import dcoms.client.ClientInterface;
import dcoms.login.LoginUI;

public class App {
        public static void main(String[] args) {
                new ClientInterface("localhost", 1040);

                // Ensure GUI runs on the Event Dispatch Thread (EDT) - good practice
                javax.swing.SwingUtilities.invokeLater(() -> {
                        LoginUI loginFrame = new LoginUI();
                        loginFrame.setLocationRelativeTo(null); // Center window
                        loginFrame.setVisible(true);
                });
        }
}
