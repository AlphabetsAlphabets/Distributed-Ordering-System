package dcoms;

import dcoms.login.LoginUI;

public class App {
    public static void main(String[] args) {
        // Ensure GUI runs on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginUI loginFrame = new LoginUI();
            loginFrame.setLocationRelativeTo(null); // Center window
            loginFrame.setVisible(true);
        });
    }
}
