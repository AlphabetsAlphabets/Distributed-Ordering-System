package dcoms;

import javax.swing.*;

import dcoms.ui.LoginRegisterPage;
import dcoms.ui.OrderPage;
import dcoms.ui.RegistrationPage;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {
    public static void main(String[] args) {
        // Ensure GUI runs on the Event Dispatch Thread (EDT) - good practice
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    Database.disconnect();
                    e.getWindow().dispose();
                }
            });

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            cardPanel.add(new LoginRegisterPage(cardLayout, cardPanel), "login");
            cardPanel.add(new OrderPage(cardLayout, cardPanel), "order");
            cardPanel.add(new RegistrationPage(cardLayout, cardPanel), "register");

            frame.add(cardPanel);
            frame.setVisible(true);
        });
    }
}
