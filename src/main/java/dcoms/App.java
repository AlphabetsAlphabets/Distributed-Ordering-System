package dcoms;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        // Ensure GUI runs on the Event Dispatch Thread (EDT) - good practice
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            cardPanel.add(new LoginRegisterPage(cardLayout, cardPanel), "login");
            cardPanel.add(new OrderPage(), "hi");

            frame.add(cardPanel);
            frame.setVisible(true);
        });
    }
}
