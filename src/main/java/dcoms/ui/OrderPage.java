package dcoms.ui;

import javax.swing.*;
import java.awt.*;

public class OrderPage extends JPanel {
    public OrderPage() {
        this.setLayout(new GridBagLayout());
        JLabel hiLabel = new JLabel("Hi");
        hiLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        this.add(hiLabel);
        JButton trackButton = new JButton("Track Orders");

        trackButton.addActionListener(e -> {
            new dcoms.ui.OrderTracking().setVisible(true);
        });

        this.add(trackButton);

    }
}
