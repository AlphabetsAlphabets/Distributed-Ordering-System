package dcoms.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderPage extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public OrderPage(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        this.setLayout(new BorderLayout());

        // Create header panel with cancel button
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            this.cardLayout.show(this.cardPanel, "login");
        });
        headerPanel.add(cancelButton);
        this.add(headerPanel, BorderLayout.NORTH);

        // Create main content panel
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create food buttons
        String[] foodItems = { "Nasi Lemak", "Mee Goreng", "Roti Canai", "Ramly Burger" };
        for (String food : foodItems) {
            JButton foodButton = new JButton(food);
            foodButton.setFont(new Font("Arial", Font.BOLD, 16));
            foodButton.setPreferredSize(new Dimension(150, 100));
            foodButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showQuantityDialog(food);
                }
            });
            contentPanel.add(foodButton);
        }

        this.add(contentPanel, BorderLayout.CENTER);
    }

    private void showQuantityDialog(String foodItem) {
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        JLabel label = new JLabel("Enter quantity for " + foodItem + ":");
        JTextField quantityField = new JTextField(10);

        panel.add(label);
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Order " + foodItem,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                if (quantity > 0) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Ordered " + quantity + " " + foodItem,
                            "Order Confirmation",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a valid quantity greater than 0",
                            "Invalid Quantity",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid number",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
